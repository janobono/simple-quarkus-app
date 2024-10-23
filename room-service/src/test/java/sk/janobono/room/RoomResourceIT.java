package sk.janobono.room;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
@QuarkusTestResource(TestPostgreSqlResource.class)
class RoomResourceIT extends RoomResourceTest {
    // Execute the same tests but in packaged mode.
}
