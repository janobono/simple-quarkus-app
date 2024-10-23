package sk.janobono.customer;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(TestPostgreSqlResource.class)
class CustomerResourceIT extends CustomerResourceTest {
    // Execute the same tests but in packaged mode.
}
