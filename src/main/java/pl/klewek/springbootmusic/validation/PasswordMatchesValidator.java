package pl.klewek.springbootmusic.validation;

import pl.klewek.springbootmusic.model.dto.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<ValidPasswordMatches, Object> {

    @Override
    public void initialize(ValidPasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context){
        UserDto userDto = (UserDto) object;
        return userDto.getPassword().equals(userDto.getMatchingPassword());
    }
}
