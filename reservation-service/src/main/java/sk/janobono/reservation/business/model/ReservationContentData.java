package sk.janobono.reservation.business.model;

import java.time.LocalDate;

public record ReservationContentData(
        Long roomId,
        Long customerId,
        LocalDate date
) {
}
