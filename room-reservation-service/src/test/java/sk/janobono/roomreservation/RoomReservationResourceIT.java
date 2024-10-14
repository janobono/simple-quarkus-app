package sk.janobono.roomreservation;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(WiremockTestResource.class)
class RoomReservationResourceIT extends RoomReservationResourceTest {
    // Execute the same tests but in packaged mode.
}
