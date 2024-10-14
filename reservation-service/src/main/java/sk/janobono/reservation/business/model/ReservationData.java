package sk.janobono.reservation.business.model;

import java.time.LocalDate;

public record ReservationData(
        Long id,
        Long roomId,
        Long customerId,
        LocalDate date
) {
}
