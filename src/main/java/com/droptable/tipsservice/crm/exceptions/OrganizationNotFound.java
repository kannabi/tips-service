package com.droptable.tipsservice.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrganizationNotFound extends ResponseStatusException {

    public OrganizationNotFound(String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }

    public OrganizationNotFound() {
        super(HttpStatus.NOT_FOUND, "Organization didn't found");
    }
}
