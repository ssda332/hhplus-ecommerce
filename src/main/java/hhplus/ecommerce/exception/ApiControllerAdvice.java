package hhplus.ecommerce.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
class ApiControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBalanceNotFoundException(MemberNotFoundException e) {
        return ResponseEntity.status(404).body(new ErrorResponse("404", e.getMessage()));
    }

}