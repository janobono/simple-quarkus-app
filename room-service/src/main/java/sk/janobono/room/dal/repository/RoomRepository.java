package sk.janobono.room.dal.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sk.janobono.room.dal.domain.RoomDo;

@ApplicationScoped
public class RoomRepository implements PanacheRepository<RoomDo> {
}
