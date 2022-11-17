package com.eliascanalesnieto.carental.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFound.class})
    protected ResponseEntity<Object> handleNotFound(NotFound ex, WebRequest request) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {BadRequest.class})
    protected ResponseEntity<Object> handleBadRequest(BadRequest ex, WebRequest request) {
        return ResponseEntity.badRequest().build();
    }

}
