package com.tradeshift.exception;

public class ServiceException extends Exception {

    private static final long serialVersionUID = 1L;
    private String code;
    private String message;

    public ServiceException(String code, String message) {

        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
