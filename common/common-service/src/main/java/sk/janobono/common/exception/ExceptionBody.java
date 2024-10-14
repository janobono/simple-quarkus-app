package sk.janobono.common.exception;

import java.time.LocalDateTime;

public record ExceptionBody(String code, String message, LocalDateTime timestamp) {
}
