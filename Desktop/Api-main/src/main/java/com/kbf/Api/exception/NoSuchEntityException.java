package com.kbf.Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchEntityException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private String message;

	public NoSuchEntityException() {}
	
	public NoSuchEntityException(String msg) {
		super(msg);
        this.message = msg;
		
	}

}
