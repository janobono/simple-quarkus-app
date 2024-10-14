package sk.janobono.roomreservation;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.util.Map;

public class WiremockTestResource implements QuarkusTestResourceLifecycleManager {

    public static WireMockServer wireMockServer;

    @Override
    public Map<String, String> start() {
        wireMockServer = new WireMockServer(57001);
        wireMockServer.start();
        return Map.of(
                "quarkus.rest-client.CustomerApiClient.url", "http://localhost:57001/api",
                "quarkus.rest-client.ReservationApiClient.url", "http://localhost:57001/api",
                "quarkus.rest-client.RoomApiClient.url", "http://localhost:57001/api"
        );
    }

    @Override
    public void stop() {
        if (wireMockServer != null) {
            wireMockServer.stop();
        }
    }
}
