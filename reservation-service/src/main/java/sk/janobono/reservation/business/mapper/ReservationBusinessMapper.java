package sk.janobono.reservation.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.reservation.business.model.ReservationData;
import sk.janobono.reservation.dal.domain.ReservationDo;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReservationBusinessMapper {

    ReservationData mapToData(ReservationDo reservation);
}
