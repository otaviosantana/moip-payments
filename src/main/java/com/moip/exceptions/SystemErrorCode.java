package com.moip.exceptions;

public enum SystemErrorCode implements ApplicationErrorCode {
    INTERNAL_SERVER_ERROR(500),
    BAD_GATEWAY(502),
    MISSING_PROPERTY(502),
    SERVICE_UNAVAILABLE(503),
    COOPERA_UNAVAILABLE(503),
    GATEWAY_TIMEOUT(504);

    final int httpStatus;

    private SystemErrorCode(final int httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }
}
