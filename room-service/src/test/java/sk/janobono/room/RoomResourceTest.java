package sk.janobono.room;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.janobono.room.api.model.Room;
import sk.janobono.room.api.model.RoomContent;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RoomResourceTest {

    @Test
    void fullRepositoryTest() {
        Room room = createRoom();
        final Room room2 = getRoom(room.getId());

        Assertions.assertEquals(room.getId(), room2.getId());
        Assertions.assertEquals(room.getName(), room2.getName());
        Assertions.assertEquals(room.getRoomNumber(), room2.getRoomNumber());

        room = updateRoom(room.getId());

        List<Room> rooms = getRooms();

        Assertions.assertEquals(1, rooms.size());
        Assertions.assertEquals(room.getId(), rooms.getFirst().getId());
        Assertions.assertEquals(room.getName(), rooms.getFirst().getName());
        Assertions.assertEquals(room.getRoomNumber(), rooms.getFirst().getRoomNumber());

        deleteRoom(room.getId());

        rooms = getRooms();
        Assertions.assertEquals(0, rooms.size());
    }

    private Room createRoom() {
        final RoomContent roomContent = new RoomContent();
        roomContent.setName("HolidayIn");
        roomContent.setRoomNumber("A100");

        final Response response = given()
                .contentType("application/json")
                .body(roomContent)
                .post("/rooms")
                .then()
                .statusCode(201)
                .extract().response();

        final Room room = response.getBody().as(Room.class);

        Assertions.assertNotNull(room);
        Assertions.assertNotNull(room.getId());
        Assertions.assertEquals(roomContent.getName(), room.getName());
        Assertions.assertEquals(roomContent.getRoomNumber(), room.getRoomNumber());

        return room;
    }

    private Room getRoom(final long id) {
        final Response response = given()
                .when().get("/rooms/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Room room = response.getBody().as(Room.class);

        Assertions.assertNotNull(room);
        Assertions.assertEquals(id, room.getId());

        return room;
    }

    private Room updateRoom(final Long id) {
        final RoomContent roomContent = new RoomContent();
        roomContent.setName("Star");
        roomContent.setRoomNumber("B123");

        final Response response = given()
                .contentType("application/json")
                .body(roomContent)
                .put("/rooms/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Room room = response.getBody().as(Room.class);

        Assertions.assertNotNull(room);
        Assertions.assertNotNull(room.getId());
        Assertions.assertEquals(roomContent.getName(), room.getName());
        Assertions.assertEquals(roomContent.getRoomNumber(), room.getRoomNumber());

        return room;
    }

    private List<Room> getRooms() {
        final Response response = given()
                .when().get("/rooms/")
                .then()
                .statusCode(200)
                .extract().response();

        final Room[] rooms = response.getBody().as(Room[].class);

        Assertions.assertNotNull(rooms);

        return Arrays.asList(rooms);
    }

    private void deleteRoom(final Long id) {
        given()
                .when().delete("/rooms/" + id)
                .then().statusCode(200);
    }
}
