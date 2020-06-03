package com.zzpj.backend.validation;

import com.zzpj.backend.DTOs.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserDto userDto = (UserDto) o;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }


}
