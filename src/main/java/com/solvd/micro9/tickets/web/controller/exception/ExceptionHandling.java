package com.solvd.micro9.tickets.web.controller.exception;

import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(WebExchangeBindException.class)
    public ExceptionBody handleException(WebExchangeBindException ex) {
        List<BindingError> bindingErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(fieldError ->
                        new BindingError(
                                fieldError.getObjectName() + "." + ((FieldError) fieldError).getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ExceptionBody("Binding errors", bindingErrors);
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    public ExceptionBody ResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        return new ExceptionBody(ex.getMessage());
    }

}
