package sk.janobono.room.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.room.business.mapper.RoomBusinessMapper;
import sk.janobono.room.business.model.RoomContentData;
import sk.janobono.room.business.model.RoomData;
import sk.janobono.room.dal.domain.RoomDo;
import sk.janobono.room.dal.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomBusinessMapper roomBusinessMapper;

    @Inject
    public RoomService(final RoomRepository roomRepository, final RoomBusinessMapper roomBusinessMapper) {
        this.roomRepository = roomRepository;
        this.roomBusinessMapper = roomBusinessMapper;
    }

    @Transactional
    public RoomData addRoom(final RoomContentData roomContent) {
        log.debug("addRoom({})", roomContent);

        final RoomDo roomDo = RoomDo.builder()
                .name(roomContent.name())
                .roomNumber(roomContent.roomNumber())
                .build();

        roomRepository.persist(roomDo);

        return roomBusinessMapper.mapToData(roomDo);
    }

    @Transactional
    public void deleteRoom(final Long id) {
        log.debug("deleteRoom({})", id);

        if (!roomRepository.deleteById(id)) {
            throw new NotFoundException("Room with id [%d] not found".formatted(id));
        }
    }

    public RoomData getRoom(final Long id) {
        log.debug("getRoom({})", id);

        return roomRepository.findByIdOptional(id)
                .map(roomBusinessMapper::mapToData)
                .orElseThrow(() -> new NotFoundException("Room with id [%d] not found".formatted(id)));
    }

    public List<RoomData> getRooms() {
        log.debug("getRooms()");

        return roomRepository.findAll().stream()
                .map(roomBusinessMapper::mapToData)
                .toList();
    }

    @Transactional
    public RoomData setRoom(final Long id, final RoomContentData roomContent) {
        log.debug("setRoom({},{})", id, roomContent);

        final Optional<RoomDo> room = roomRepository.findByIdOptional(id);
        if (room.isEmpty()) {
            throw new NotFoundException("Room with id [%d] not found".formatted(id));
        }

        final RoomDo roomDo = room.get();
        roomDo.setName(roomContent.name());
        roomDo.setRoomNumber(roomContent.roomNumber());

        roomRepository.persist(roomDo);

        return roomBusinessMapper.mapToData(roomDo);
    }
}
