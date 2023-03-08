package com.solvd.micro9.tickets.web.controller.exception;

import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ExceptionBody> handleException(WebExchangeBindException ex) {
        List<BindingError> bindingErrors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(fieldError ->
                        new BindingError(
                                fieldError.getObjectName() + "." + ((FieldError) fieldError).getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return Mono.just(new ExceptionBody("Binding errors", bindingErrors));
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    public Mono<ExceptionBody> ResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        return Mono.just(new ExceptionBody(ex.getMessage()));
    }

}
