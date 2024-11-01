package sk.janobono.reservation.business.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import sk.janobono.reservation.business.mapper.ReservationBusinessMapper;
import sk.janobono.reservation.business.model.ReservationContentData;
import sk.janobono.reservation.business.model.ReservationData;
import sk.janobono.reservation.dal.domain.ReservationDo;
import sk.janobono.reservation.dal.repository.ReservationRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservationBusinessMapper reservationBusinessMapper;

    @Inject
    public ReservationService(final ReservationRepository reservationRepository, final ReservationBusinessMapper reservationBusinessMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationBusinessMapper = reservationBusinessMapper;
    }

    @Transactional
    public ReservationData addReservation(final ReservationContentData reservationContent) {
        log.debug("addReservation({})", reservationContent);

        final ReservationDo reservationDo = ReservationDo.builder()
                .customerId(reservationContent.customerId())
                .roomId(reservationContent.roomId())
                .date(reservationContent.date())
                .build();

        reservationRepository.persist(reservationDo);

        return reservationBusinessMapper.mapToData(reservationDo);
    }

    @Transactional
    public void deleteReservation(final Long id) {
        log.debug("deleteReservation({})", id);

        if (!reservationRepository.deleteById(id)) {
            throw new NotFoundException("Reservation with id [%d] not found".formatted(id));
        }
    }

    public ReservationData getReservation(final Long id) {
        log.debug("getReservation({})", id);

        return reservationRepository.findByIdOptional(id)
                .map(reservationBusinessMapper::mapToData)
                .orElseThrow(() -> new NotFoundException("Reservation with id [%d] not found".formatted(id)));
    }

    public List<ReservationData> getReservations(final LocalDate date) {
        log.debug("getReservations({})", date);

        return Optional.ofNullable(date)
                .map(reservationDate -> reservationRepository.find("date", reservationDate))
                .orElseGet(reservationRepository::findAll)
                .stream()
                .map(reservationBusinessMapper::mapToData)
                .toList();
    }

    @Transactional
    public ReservationData setReservation(final Long id, final ReservationContentData reservationContent) {
        log.debug("setReservation({},{})", id, reservationContent);

        final Optional<ReservationDo> reservation = reservationRepository.findByIdOptional(id);
        if (reservation.isEmpty()) {
            throw new NotFoundException("Reservation with id [%d] not found".formatted(id));
        }

        final ReservationDo reservationDo = reservation.get();
        reservationDo.setCustomerId(reservationContent.customerId());
        reservationDo.setRoomId(reservationContent.roomId());
        reservationDo.setDate(reservationContent.date());

        reservationRepository.persist(reservationDo);

        return reservationBusinessMapper.mapToData(reservationDo);
    }
}
