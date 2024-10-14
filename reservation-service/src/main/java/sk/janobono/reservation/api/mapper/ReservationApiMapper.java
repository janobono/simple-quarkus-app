package sk.janobono.reservation.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.reservation.api.model.Reservation;
import sk.janobono.reservation.api.model.ReservationContent;
import sk.janobono.reservation.business.model.ReservationContentData;
import sk.janobono.reservation.business.model.ReservationData;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ReservationApiMapper {

    Reservation mapToApi(ReservationData reservation);

    ReservationContentData mapToData(ReservationContent reservationContent);
}
