package sk.janobono.common.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Provider
public class ServiceExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = Logger.getLogger(ServiceExceptionMapper.class);

    @Override
    public Response toResponse(final Throwable throwable) {
        LOG.warn("something went wrong", throwable);
        return switch (throwable) {
            case final ServiceException serviceException -> mapException(serviceException);
            case final WebApplicationException webApplicationException -> mapException(webApplicationException);
            default -> mapException(throwable);
        };
    }

    private Response mapException(final ServiceException serviceException) {
        final ExceptionBody exceptionBody = new ExceptionBody(
                serviceException.getCode(),
                serviceException.getMessage(),
                LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS)
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(exceptionBody).build();
    }

    private Response mapException(final WebApplicationException webApplicationException) {
        final ExceptionBody exceptionBody = new ExceptionBody(
                "UNKNOWN",
                webApplicationException.getMessage(),
                LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS)
        );
        return Response.status(webApplicationException.getResponse().getStatus()).entity(exceptionBody).build();
    }

    private Response mapException(final Throwable throwable) {
        final ExceptionBody exceptionBody = new ExceptionBody(
                "UNKNOWN",
                throwable.getClass().getName(),
                LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS)
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(exceptionBody).build();
    }
}
