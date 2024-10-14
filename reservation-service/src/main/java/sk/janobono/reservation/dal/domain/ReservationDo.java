package sk.janobono.reservation.dal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sqa_reservation")
public class ReservationDo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "reservation_date")
    private LocalDate date;
}
