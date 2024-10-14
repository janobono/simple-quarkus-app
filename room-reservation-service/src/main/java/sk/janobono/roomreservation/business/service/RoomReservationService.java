package sk.janobono.roomreservation.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import sk.janobono.roomreservation.business.model.RoomReservationData;
import sk.janobono.roomreservation.client.CustomerApiClient;
import sk.janobono.roomreservation.client.ReservationApiClient;
import sk.janobono.roomreservation.client.RoomApiClient;
import sk.janobono.roomreservation.client.customer.model.Customer;
import sk.janobono.roomreservation.client.reservation.model.Reservation;
import sk.janobono.roomreservation.client.room.model.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@ApplicationScoped
public class RoomReservationService {

    @RestClient
    CustomerApiClient customerApiClient;

    @RestClient
    ReservationApiClient reservationApiClient;

    @RestClient
    RoomApiClient roomApiClient;

    public List<RoomReservationData> getRoomReservations(final LocalDate date) {
        log.debug("getRoomReservations({})", date);

        final List<Reservation> reservations = reservationApiClient.getReservations(date.format(DateTimeFormatter.ISO_DATE));

        return reservations.stream()
                .map(this::mapRoomReservation)
                .toList();
    }

    private RoomReservationData mapRoomReservation(final Reservation reservation) {
        final Room room = roomApiClient.getRoom(reservation.getRoomId());
        final Customer customer = customerApiClient.getCustomer(reservation.getCustomerId());

        return RoomReservationData.builder()
                .roomId(reservation.getRoomId())
                .customerId(reservation.getCustomerId())
                .roomName(room.getName())
                .roomNumber(room.getRoomNumber())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .date(reservation.getDate())
                .build();
    }
}
