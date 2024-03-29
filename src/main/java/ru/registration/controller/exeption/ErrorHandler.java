package ru.registration.controller.exeption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.registration.exeption.NotFoundException;
import ru.registration.exeption.ValidatorException;

import java.util.Map;

/**
 * @author MR.k0F31n
 */
@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFoundException(final NotFoundException exception) {
        log.warn("Error! Not Found, server status: '{}' text message: '{}'", HttpStatus.NOT_FOUND, exception.getMessage());
        return Map.of("Not found object", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleNullPointerException(final NullPointerException exception) {
        log.warn("Error! NullPointer, server status: '{}' text message: '{}'",
                HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return Map.of("Null detected, check your actions", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handlerValidationException(final ValidatorException exception) {
        log.warn("Error! Validation fault, server status: '{}' text message: '{}'",
                HttpStatus.BAD_REQUEST, exception.getMessage());
        return Map.of("Validation fault, check your actions", exception.getMessage());
    }
}
