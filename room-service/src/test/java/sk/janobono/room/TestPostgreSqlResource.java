package sk.janobono.room;

import io.quarkus.test.common.DevServicesContext;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import java.util.Map;
import java.util.Optional;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;

public class TestPostgreSqlResource implements QuarkusTestResourceLifecycleManager, DevServicesContext.ContextAware {

    private Optional<String> containerNetworkId = Optional.empty();
    private JdbcDatabaseContainer container;

    @Override
    public void setIntegrationTestContext(final DevServicesContext context) {
        containerNetworkId = context.containerNetworkId();
    }

    @Override
    public Map<String, String> start() {
        // start a container making sure to call withNetworkMode() with the value of containerNetworkId if present
        container = new PostgreSQLContainer("postgres:alpine");

        // apply the network to the container
        containerNetworkId.ifPresent(container::withNetworkMode);

        // start container before retrieving its URL or other properties
        container.start();

        String jdbcUrl = container.getJdbcUrl();
        if (containerNetworkId.isPresent()) {
            // Replace hostname + port in the provided JDBC URL with the hostname of the Docker container
            // running PostgreSQL and the listening port.
            jdbcUrl = fixJdbcUrl(jdbcUrl);
        }

        // return a map containing the configuration the application needs to use the service
        return ImmutableMap.of(
            "quarkus.datasource.username", container.getUsername(),
            "quarkus.datasource.password", container.getPassword(),
            "quarkus.datasource.jdbc.url", jdbcUrl
        );
    }

    private String fixJdbcUrl(final String jdbcUrl) {
        // Part of the JDBC URL to replace
        final String hostPort = container.getHost() + ':' + container.getMappedPort(PostgreSQLContainer.POSTGRESQL_PORT);
        // Host/IP on the container network plus the unmapped port
        final String networkHostPort = container.getCurrentContainerInfo().getConfig().getHostName() + ':' + PostgreSQLContainer.POSTGRESQL_PORT;

        return jdbcUrl.replace(hostPort, networkHostPort);
    }

    @Override
    public void stop() {
        container.stop();
    }
}
