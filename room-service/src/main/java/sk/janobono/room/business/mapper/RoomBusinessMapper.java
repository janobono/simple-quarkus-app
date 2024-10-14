package sk.janobono.room.business.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.room.business.model.RoomData;
import sk.janobono.room.dal.domain.RoomDo;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoomBusinessMapper {

    RoomData mapToData(RoomDo room);
}
