package com.zzpj.backend.exceptions;

public class AppBaseException extends Exception{

    public AppBaseException(String message) {
        super(message);
    }

    public AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
