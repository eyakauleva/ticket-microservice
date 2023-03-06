package com.solvd.micro9.tickets.web.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BindingError {

    private String field;

    private String message;

}
