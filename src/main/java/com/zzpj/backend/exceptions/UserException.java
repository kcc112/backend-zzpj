package com.zzpj.backend.exceptions;

public class UserException extends AppBaseException {

    public static final String USER_NOT_FOUND = "user.not.found.problem";

    public UserException(String msg){
        super(msg);
    }

    public static UserException exceptionForUserNotFound(){
        return new UserException(USER_NOT_FOUND);
    }
}
