package com.zzpj.backend.exceptions;

import javax.security.auth.login.AccountException;

public class UserException extends AppBaseException {
    public static final String EMAIL_EXISTS_PROBLEM = "account.with.this.email.exists.problem";

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    static public UserException createExceptionEmailExists(){
        return new UserException(EMAIL_EXISTS_PROBLEM);
    }
}
