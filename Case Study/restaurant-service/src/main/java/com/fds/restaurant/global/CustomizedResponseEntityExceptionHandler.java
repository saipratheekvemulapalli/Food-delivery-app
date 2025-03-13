package com.fds.restaurant.global;

import java.time.LocalDate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.fds.restaurant.exception.RestaurantNotFoundException;
import com.fds.restaurant.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

   
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        // Log the error for debugging
        log.error("An error occurred: ", ex);

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDate.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                "Conflict");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(RestaurantNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundException(RestaurantNotFoundException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDate.now(),
                ex.getMessage(),
                "Not Found");
        log.warn("Restaurant not found: {}", ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        StringBuilder sb = new StringBuilder();
        for (FieldError fe : ex.getBindingResult().getFieldErrors()) {
            sb.append(fe.getField())
              .append(": ")
              .append(fe.getDefaultMessage())
              .append(". ");
        }

        ExceptionResponse exceptionResponse = new ExceptionResponse(
                LocalDate.now(),
                "Validation Fails",
                sb.toString());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
