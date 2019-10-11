package com.auth.api.model;

import lombok.Getter;

@Getter
public class ValidationError {
    private final int status;
    private final String message;

    public ValidationError(int status, String message) {
        this.status = status;
        this.message = message;
    }

}