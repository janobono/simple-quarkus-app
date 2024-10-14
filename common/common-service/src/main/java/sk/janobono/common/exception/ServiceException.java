package sk.janobono.common.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    private final String code;

    public ServiceException(final String code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }
}
