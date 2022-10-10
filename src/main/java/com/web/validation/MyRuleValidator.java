package com.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyRuleValidator implements ConstraintValidator<MyRule,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value==null){
            return false;
        }
        return value.startsWith("MY");
    }
}
