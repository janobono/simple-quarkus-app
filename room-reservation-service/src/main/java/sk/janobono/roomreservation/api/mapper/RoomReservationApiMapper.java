package sk.janobono.roomreservation.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.roomreservation.api.model.RoomReservation;
import sk.janobono.roomreservation.business.model.RoomReservationData;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoomReservationApiMapper {

    RoomReservation mapToApi(RoomReservationData roomReservation);
}
