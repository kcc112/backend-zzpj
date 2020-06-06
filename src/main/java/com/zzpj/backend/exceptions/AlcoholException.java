package com.zzpj.backend.exceptions;

public class AlcoholException extends AppBaseException {
    public static final String ALCOHOL_WITH_GIVEN_NAME_EXIST = "alcohol with given name exist";

    public AlcoholException(String message) {
        super(message);
    }

    public AlcoholException(String message, Throwable e) {
        super(message, e);
    }

    public static AlcoholException createAlcoholWithGivenNameExistException() {
        return new AlcoholException(ALCOHOL_WITH_GIVEN_NAME_EXIST);
    }
}
