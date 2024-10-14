package sk.janobono.roomreservation;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.janobono.roomreservation.api.model.RoomReservation;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static io.restassured.RestAssured.given;

@QuarkusTest
class RoomReservationResourceTest {

    @Test
    void fullRepositoryTest() {
        final LocalDate today = LocalDate.now();

        WiremockTestResource.wireMockServer.stubFor(
                WireMock.get("/reservations?date="+ today.format(DateTimeFormatter.ISO_DATE))
                        .willReturn(WireMock.aResponse().withJsonBody( new ObjectMapper().valueToTree(new ArrayList<>())))
        );

        final List<RoomReservation> reservations = getRoomReservations(today);
        Assertions.assertEquals(0, reservations.size());
    }

    private List<RoomReservation> getRoomReservations(final LocalDate date) {
        final Response response = given()
                .when().get("/room-reservations/" + date.format(DateTimeFormatter.ISO_DATE))
                .then()
                .statusCode(200)
                .extract().response();

        final RoomReservation[] reservations = response.getBody().as(RoomReservation[].class);

        Assertions.assertNotNull(reservations);

        return Arrays.asList(reservations);
    }
}
