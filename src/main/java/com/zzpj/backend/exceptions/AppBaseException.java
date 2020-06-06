package com.zzpj.backend.exceptions;

public class AppBaseException extends Exception {

    public AppBaseException() {
        super("Invalid data");
    }

    public AppBaseException(String message) {
        super(message);
    }

    public AppBaseException(String message, Throwable e) {
        super(message, e);
    }
}
