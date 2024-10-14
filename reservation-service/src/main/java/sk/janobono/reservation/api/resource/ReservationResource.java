package sk.janobono.reservation.api.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.reservation.api.ReservationsApi;
import sk.janobono.reservation.api.mapper.ReservationApiMapper;
import sk.janobono.reservation.api.model.ReservationContent;
import sk.janobono.reservation.business.model.ReservationData;
import sk.janobono.reservation.business.service.ReservationService;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public class ReservationResource implements ReservationsApi {

    private final ReservationService reservationService;
    private final ReservationApiMapper reservationApiMapper;

    @Inject
    public ReservationResource(final ReservationService reservationService, final ReservationApiMapper reservationApiMapper) {
        this.reservationService = reservationService;
        this.reservationApiMapper = reservationApiMapper;
    }

    @Override
    public Response addReservation(final ReservationContent reservationContent) {
        log.debug("addReservation({})", reservationContent);
        final ReservationData reservation = reservationService.addReservation(reservationApiMapper.mapToData(reservationContent));
        return Response.status(Response.Status.CREATED).entity(reservationApiMapper.mapToApi(reservation)).build();
    }

    @Override
    public Response deleteReservation(final Long id) {
        log.debug("deleteReservation({})", id);
        reservationService.deleteReservation(id);
        return Response.ok().build();
    }

    @Override
    public Response getReservation(final Long id) {
        log.debug("getReservation({})", id);
        final ReservationData reservation = reservationService.getReservation(id);
        return Response.ok(reservationApiMapper.mapToApi(reservation)).build();
    }

    @Override
    public Response getReservations(final LocalDate date) {
        log.debug("getReservations({})", date);
        final List<ReservationData> reservations = reservationService.getReservations(date);
        return Response.ok(
                reservations.stream()
                        .map(reservationApiMapper::mapToApi)
                        .toList()
        ).build();
    }

    @Override
    public Response setReservation(final Long id, final ReservationContent reservationContent) {
        log.debug("setReservation({},{})", id, reservationContent);
        final ReservationData reservation = reservationService.setReservation(id, reservationApiMapper.mapToData(reservationContent));
        return Response.ok(reservationApiMapper.mapToApi(reservation)).build();
    }
}
