package com.zzpj.backend.exceptions;

public class AppBaseException extends Exception {

    public AppBaseException() {
        super("Invalid data");
    }

    public AppBaseException(String msg){
        super(msg);
    }
}
