package pl.mcm.carrental.utils;

import org.springframework.beans.BeanWrapperImpl;
import pl.mcm.carrental.annotation.PasswordValueMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordFieldsValueMatchValidator implements ConstraintValidator<PasswordValueMatch, Object> {

    private String field;
    private String fieldMatch;
    private String message;

    @Override
    public void initialize(PasswordValueMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue =  new BeanWrapperImpl(value)
                .getPropertyValue(this.fieldMatch);

        boolean isValid = false;

        if(fieldValue != null) {
            isValid = fieldValue.equals(fieldMatchValue);
        }

        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(fieldMatch)
                .addConstraintViolation();
    }
        return isValid;
    }
}
