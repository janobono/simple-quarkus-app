package sk.janobono.roomreservation.business.model;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RoomReservationData(
        Long roomId,
        Long customerId,
        String roomName,
        String roomNumber,
        String firstName,
        String lastName,
        LocalDate date
) {
}
