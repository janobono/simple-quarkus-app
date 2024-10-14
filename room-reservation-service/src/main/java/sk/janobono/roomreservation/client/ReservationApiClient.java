package sk.janobono.roomreservation.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sk.janobono.roomreservation.client.reservation.model.Reservation;

import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

@Path("/reservations")
@RegisterRestClient(configKey = "ReservationApiClient")
public interface ReservationApiClient {

    @GET
    @Timeout(100)
    @Retry
    @Fallback(fallbackMethod = "getFallbackReservations")
    @CircuitBreaker(delay = 1, delayUnit = ChronoUnit.MINUTES)
    List<Reservation> getReservations(@QueryParam("date") String date);

    default List<Reservation> getFallbackReservations(final String date) {
        return Collections.emptyList();
    }
}
