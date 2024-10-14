package sk.janobono.room.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.room.api.RoomsApi;
import sk.janobono.room.api.mapper.RoomApiMapper;
import sk.janobono.room.api.model.RoomContent;
import sk.janobono.room.business.model.RoomData;
import sk.janobono.room.business.service.RoomService;

import java.util.List;

@Slf4j
public class RoomResource implements RoomsApi {

    private final RoomService roomService;
    private final RoomApiMapper roomApiMapper;

    @Inject
    public RoomResource(final RoomService roomService, final RoomApiMapper roomApiMapper) {
        this.roomService = roomService;
        this.roomApiMapper = roomApiMapper;
    }

    @Override
    public Response addRoom(final RoomContent roomContent) {
        log.debug("addRoom({})", roomContent);
        final RoomData room = roomService.addRoom(roomApiMapper.mapToData(roomContent));
        return Response.status(Response.Status.CREATED).entity(roomApiMapper.mapToApi(room)).build();
    }

    @Override
    public Response deleteRoom(final Long id) {
        log.debug("deleteRoom({})", id);
        roomService.deleteRoom(id);
        return Response.ok().build();
    }

    @Override
    public Response getRoom(final Long id) {
        log.debug("getRoom({})", id);
        final RoomData room = roomService.getRoom(id);
        return Response.ok(roomApiMapper.mapToApi(room)).build();
    }

    @Override
    public Response getRooms() {
        log.debug("getRooms()");
        final List<RoomData> rooms = roomService.getRooms();
        return Response.ok(
                rooms.stream()
                        .map(roomApiMapper::mapToApi)
                        .toList()
        ).build();
    }

    @Override
    public Response setRoom(final Long id, final RoomContent roomContent) {
        log.debug("setRoom({},{})", id, roomContent);
        final RoomData room = roomService.setRoom(id, roomApiMapper.mapToData(roomContent));
        return Response.ok(roomApiMapper.mapToApi(room)).build();
    }
}
