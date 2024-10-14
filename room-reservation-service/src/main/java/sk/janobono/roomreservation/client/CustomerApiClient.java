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
import sk.janobono.roomreservation.client.customer.model.Customer;

import java.time.temporal.ChronoUnit;

@Path("/customers")
@RegisterRestClient(configKey = "CustomerApiClient")
public interface CustomerApiClient {

    @GET
    @Path("/{id}")
    @Produces({"application/json"})
    @Timeout(100)
    @Retry
    @Fallback(fallbackMethod = "getFallbackCustomer")
    @CircuitBreaker(delay = 1, delayUnit = ChronoUnit.MINUTES)
    Customer getCustomer(@PathParam("id") Long id);

    default Customer getFallbackCustomer(Long id) {
        return new Customer();
    }
}
