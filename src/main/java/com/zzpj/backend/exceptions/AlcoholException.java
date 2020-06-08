package com.zzpj.backend.exceptions;



public class AlcoholException extends AppBaseException{
    public static final String ALCOHOL_NOT_FOUND = "alcohol.not.found.problem";
    public static final String TOO_FEW_ALCOHOL = "too.few.alcohol.problem";

    public AlcoholException(String msg){
        super(msg);
    }

    public static AlcoholException exceptionForAlcoholNotFound(){
        return new AlcoholException(ALCOHOL_NOT_FOUND);
    }

    public static AlcoholException exceptionToFewAlcohol(){
        return new AlcoholException(TOO_FEW_ALCOHOL);
    }
}
