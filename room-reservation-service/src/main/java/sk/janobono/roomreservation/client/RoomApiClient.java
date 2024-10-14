package sk.janobono.roomreservation.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import sk.janobono.roomreservation.client.room.model.Room;

import java.time.temporal.ChronoUnit;

@Path("/rooms")
@RegisterRestClient(configKey = "RoomApiClient")
public interface RoomApiClient {

    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    @Timeout(100)
    @Retry
    @Fallback(fallbackMethod = "getFallbackRoom")
    @CircuitBreaker(delay = 1, delayUnit = ChronoUnit.MINUTES)
    Room getRoom(@PathParam("id") Long id);

    default Room getFallbackRoom(Long id) {
        return new Room();
    }
}
