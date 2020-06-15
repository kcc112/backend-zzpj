package com.zzpj.backend.validation;

import com.zzpj.backend.dto.UserDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        //The default implementation is a no-op.
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        UserDTO userDto = (UserDTO) object;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }


}
