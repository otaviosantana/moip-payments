package com.moip.exceptions;

public enum UserErrorCode implements ApplicationErrorCode {
    INVALID_DATA(422),
    TOO_MANY_REQUEST(429),
    BAD_REQUEST(400),
    CONFLICT(409),
    NOT_FOUND(404);

    final int httpStatus;

    private UserErrorCode(final int httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }
}
