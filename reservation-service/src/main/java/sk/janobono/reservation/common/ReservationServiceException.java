package sk.janobono.reservation.common;

import sk.janobono.common.exception.ServiceException;

import java.text.MessageFormat;

public enum ReservationServiceException {

    SOME_SERVICE_SPECIFIC_EXCEPTION;

    public ServiceException exception(final String pattern, final Object... arguments) {
        return exception(null, pattern, arguments);
    }

    public ServiceException exception(final Throwable cause, final String pattern, final Object... arguments) {
        return new ServiceException(this.name(), MessageFormat.format(pattern, arguments), cause);
    }
}
