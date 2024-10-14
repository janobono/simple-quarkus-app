package sk.janobono.room.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValueCheckStrategy;
import sk.janobono.room.api.model.Room;
import sk.janobono.room.api.model.RoomContent;
import sk.janobono.room.business.model.RoomContentData;
import sk.janobono.room.business.model.RoomData;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RoomApiMapper {

    Room mapToApi(RoomData room);

    RoomContentData mapToData(RoomContent roomContent);
}
