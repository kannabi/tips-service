package com.droptable.tipsservice.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTokenException extends ResponseStatusException {

	public InvalidTokenException(String reason) {
		super(HttpStatus.CONFLICT, reason);
	}
}
