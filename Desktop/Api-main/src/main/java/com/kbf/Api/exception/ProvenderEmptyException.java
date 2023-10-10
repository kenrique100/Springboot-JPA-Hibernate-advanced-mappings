package com.kbf.Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProvenderEmptyException extends RuntimeException {

    private static final long serialVersionUID = -322785875309006158L;

	public ProvenderEmptyException(String message) {
        super(message);
    }
}
