package sk.janobono.reservation.dal.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.janobono.reservation.dal.domain.ReservationDo;

@ApplicationScoped
public class ReservationRepository implements PanacheRepository<ReservationDo> {
}
