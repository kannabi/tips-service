package com.droptable.tipsservice.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class WaiterNotFound extends ResponseStatusException {

    public WaiterNotFound(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public WaiterNotFound() {
        super(HttpStatus.NOT_FOUND, "Waiter didn't found");
    }
}
