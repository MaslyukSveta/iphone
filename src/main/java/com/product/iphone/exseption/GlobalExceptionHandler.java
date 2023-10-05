package com.product.iphone.exseption;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handle runtime exceptions by returning an INTERNAL_SERVER_ERROR response.
     *
     * @param ex The runtime exception to handle.
     * @return A ResponseEntity with an error message and an INTERNAL_SERVER_ERROR status.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
    /**
     * Handle specific exceptions (ProductNotFoundException and OrderNotFoundException) by returning a NOT_FOUND response.
     *
     * @param ex The exception to handle.
     * @return A ResponseEntity with an error message and a NOT_FOUND status.
     */
    @ExceptionHandler({ProductNotFoundException.class, OrderNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    /**
     * Handle InsufficientProductQuantityException by returning a BAD_REQUEST response.
     *
     * @param ex The exception to handle.
     * @return A ResponseEntity with an error message and a BAD_REQUEST status.
     */
    @ExceptionHandler(InsufficientProductQuantityException.class)
    public ResponseEntity<String> handleInsufficientQuantity(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    /**
     * Handle MethodArgumentNotValidException by extracting validation error messages and returning a BAD_REQUEST response.
     *
     * @param ex The exception to handle.
     * @return A ResponseEntity with validation error messages and a BAD_REQUEST status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors().stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}