package com.solvd.micro9.tickets.web.controller.exception;

import com.solvd.micro9.tickets.domain.exception.ResourceDoesNotExistException;
import com.solvd.micro9.tickets.domain.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ExceptionBody> handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        return Mono.just(new ExceptionBody(ex.getMessage()));
    }

    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ExceptionBody> handleServerException(ServerException ex) {
        return Mono.just(new ExceptionBody(ex.getMessage()));
    }

}
