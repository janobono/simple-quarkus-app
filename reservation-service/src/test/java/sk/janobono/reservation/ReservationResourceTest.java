package sk.janobono.reservation;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import sk.janobono.reservation.api.model.Reservation;
import sk.janobono.reservation.api.model.ReservationContent;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ReservationResourceTest {

    @Test
    void fullRepositoryTest() {
        Reservation reservation = createReservation();
        final Reservation reservation2 = getReservation(reservation.getId());

        Assertions.assertEquals(reservation.getId(), reservation2.getId());
        Assertions.assertEquals(reservation.getCustomerId(), reservation2.getCustomerId());
        Assertions.assertEquals(reservation.getRoomId(), reservation2.getRoomId());
        Assertions.assertEquals(reservation.getDate(), reservation2.getDate());

        reservation = updateReservation(reservation.getId());

        List<Reservation> reservations = getReservations();

        Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals(reservation.getId(), reservations.getFirst().getId());
        Assertions.assertEquals(reservation.getCustomerId(), reservations.getFirst().getCustomerId());
        Assertions.assertEquals(reservation.getRoomId(), reservations.getFirst().getRoomId());
        Assertions.assertEquals(reservation.getDate(), reservations.getFirst().getDate());

        reservations = getReservations(reservation.getDate());

        Assertions.assertEquals(1, reservations.size());
        Assertions.assertEquals(reservation.getId(), reservations.getFirst().getId());
        Assertions.assertEquals(reservation.getCustomerId(), reservations.getFirst().getCustomerId());
        Assertions.assertEquals(reservation.getRoomId(), reservations.getFirst().getRoomId());
        Assertions.assertEquals(reservation.getDate(), reservations.getFirst().getDate());

        deleteReservation(reservation.getId());

        reservations = getReservations();
        Assertions.assertEquals(0, reservations.size());
    }

    private Reservation createReservation() {
        final ReservationContent reservationContent = new ReservationContent();
        reservationContent.setCustomerId(1L);
        reservationContent.setRoomId(1L);
        reservationContent.setDate(LocalDate.of(2024, 10, 15));

        final Response response = given()
                .contentType("application/json")
                .body(reservationContent)
                .post("/reservations")
                .then()
                .statusCode(201)
                .extract().response();

        final Reservation reservation = response.getBody().as(Reservation.class);

        Assertions.assertNotNull(reservation);
        Assertions.assertNotNull(reservation.getId());
        Assertions.assertEquals(reservationContent.getCustomerId(), reservation.getCustomerId());
        Assertions.assertEquals(reservationContent.getRoomId(), reservation.getRoomId());
        Assertions.assertEquals(reservationContent.getDate(), reservation.getDate());

        return reservation;
    }

    private Reservation getReservation(final long id) {
        final Response response = given()
                .when().get("/reservations/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Reservation reservation = response.getBody().as(Reservation.class);

        Assertions.assertNotNull(reservation);
        Assertions.assertEquals(id, reservation.getId());

        return reservation;
    }

    private Reservation updateReservation(final Long id) {
        final ReservationContent reservationContent = new ReservationContent();
        reservationContent.setCustomerId(2L);
        reservationContent.setRoomId(2L);
        reservationContent.setDate(LocalDate.of(2024, 10, 16));

        final Response response = given()
                .contentType("application/json")
                .body(reservationContent)
                .put("/reservations/" + id)
                .then()
                .statusCode(200)
                .extract().response();

        final Reservation reservation = response.getBody().as(Reservation.class);

        Assertions.assertNotNull(reservation);
        Assertions.assertNotNull(reservation.getId());
        Assertions.assertEquals(reservationContent.getCustomerId(), reservation.getCustomerId());
        Assertions.assertEquals(reservationContent.getRoomId(), reservation.getRoomId());
        Assertions.assertEquals(reservationContent.getDate(), reservation.getDate());

        return reservation;
    }

    private List<Reservation> getReservations() {
        final Response response = given()
                .when().get("/reservations/")
                .then()
                .statusCode(200)
                .extract().response();

        final Reservation[] reservations = response.getBody().as(Reservation[].class);

        Assertions.assertNotNull(reservations);

        return Arrays.asList(reservations);
    }

    private List<Reservation> getReservations(final LocalDate date) {
        final Response response = given()
                .when().get("/reservations/?date=" + date.format(DateTimeFormatter.ISO_DATE))
                .then()
                .statusCode(200)
                .extract().response();

        final Reservation[] reservations = response.getBody().as(Reservation[].class);

        Assertions.assertNotNull(reservations);

        return Arrays.asList(reservations);
    }

    private void deleteReservation(final Long id) {
        given()
                .when().delete("/reservations/" + id)
                .then().statusCode(200);
    }
}
