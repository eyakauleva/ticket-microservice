package com.solvd.micro9.tickets.web.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleBindingExceptions(BindException ex) {
        List<BindingError> bindingErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError ->
                        new BindingError(
                                fieldError.getObjectName() + "." + fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ExceptionBody("Binding errors", bindingErrors);
    }

}
