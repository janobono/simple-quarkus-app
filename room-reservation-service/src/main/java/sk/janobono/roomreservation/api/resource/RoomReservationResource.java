package sk.janobono.roomreservation.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.roomreservation.api.RoomReservationsApi;
import sk.janobono.roomreservation.api.mapper.RoomReservationApiMapper;
import sk.janobono.roomreservation.business.model.RoomReservationData;
import sk.janobono.roomreservation.business.service.RoomReservationService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class RoomReservationResource implements RoomReservationsApi {

    private final RoomReservationService roomReservationService;
    private final RoomReservationApiMapper roomReservationApiMapper;

    @Inject
    public RoomReservationResource(final RoomReservationService roomReservationService, final RoomReservationApiMapper roomReservationApiMapper) {
        this.roomReservationService = roomReservationService;
        this.roomReservationApiMapper = roomReservationApiMapper;
    }

    @Override
    public Response getRoomReservations(final LocalDate date) {
        log.debug("getRoomReservations({})", date);

        final List<RoomReservationData> reservations = roomReservationService.getRoomReservations(date);
        return Response.ok(
                reservations.stream()
                        .map(roomReservationApiMapper::mapToApi)
                        .toList()
        ).build();
    }
}
