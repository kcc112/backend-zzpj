package com.zzpj.backend.exceptions;


public class UserException extends AppBaseException {
    public static final String EMAIL_EXISTS_PROBLEM = "account.with.this.email.exists.problem";
    public static final String USER_NOT_FOUND = "user.not.found.problem";
    public static final String INCORRECT_CREDENTIALS = "incorrect.username.or.password.problem";


    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    static public UserException createExceptionEmailExists() {
        return new UserException(EMAIL_EXISTS_PROBLEM);
    }

    public static UserException exceptionForUserNotFound() {
        return new UserException(USER_NOT_FOUND);
    }
    public static UserException createExceptionIncorrectCredentials(Throwable cause){
        return new UserException(INCORRECT_CREDENTIALS, cause);
    }

}
