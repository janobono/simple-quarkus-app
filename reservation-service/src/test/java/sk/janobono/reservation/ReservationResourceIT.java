package sk.janobono.reservation;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(TestMsSqlResource.class)
class ReservationResourceIT extends ReservationResourceTest {
    // Execute the same tests but in packaged mode.
}
