package com.droptable.tipsservice.crm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class OrganizationAlreadyExist extends ResponseStatusException {

    public OrganizationAlreadyExist(String reason) {
        super(HttpStatus.CONFLICT, reason);
    }
}