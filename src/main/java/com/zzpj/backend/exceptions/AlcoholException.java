package com.zzpj.backend.exceptions;

public class AlcoholException extends AppBaseException {

    public static final String ALCOHOL_WITH_GIVEN_NAME_EXIST = "alcohol with given name exist";
    public static final String ALCOHOL_NOT_FOUND = "alcohol.not.found.problem";
    public static final String TOO_FEW_ALCOHOL = "too.few.alcohol.problem";

    public AlcoholException(String message) {
        super(message);
    }

    public static AlcoholException createAlcoholWithGivenNameExistException() {
        return new AlcoholException(ALCOHOL_WITH_GIVEN_NAME_EXIST);
    }

    public static AlcoholException exceptionForAlcoholNotFound() {
        return new AlcoholException(ALCOHOL_NOT_FOUND);
    }

    public static AlcoholException exceptionToFewAlcohol() {
        return new AlcoholException(TOO_FEW_ALCOHOL);
    }
}
