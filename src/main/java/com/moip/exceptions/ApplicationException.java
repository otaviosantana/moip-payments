package com.moip.exceptions;

import java.util.HashMap;
import java.util.Map;

public abstract class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = 5159913781729993872L;

    private final Map<String, Object> data = new HashMap<>();
    private final ApplicationErrorCode code;

    public ApplicationException(final ApplicationErrorCode code) {
        super();
        this.code = code;
    }

    public ApplicationException(final ApplicationErrorCode code, final Throwable cause) {
        super(code.toString(), cause);
        this.code = code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ApplicationErrorCode getCode() {
        return code;
    }

    public ApplicationException set(final String key, final Object value) {
        data.put(key, value);
        return this;
    }
}
