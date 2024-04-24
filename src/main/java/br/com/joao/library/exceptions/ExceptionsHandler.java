package br.com.joao.library.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                HttpStatus.NOT_FOUND.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(InvalidArgumentsException.class)
    public ResponseEntity<StandardError> invalidArgument(InvalidArgumentsException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Invalid input",
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        StandardError err = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                "Argument invalid",
                Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
