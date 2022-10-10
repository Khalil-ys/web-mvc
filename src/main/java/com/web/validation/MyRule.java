package com.web.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy=MyRuleValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRule {

    String message() default "Kurs kodu MY ile baslamalidir!";
    Class<? extends Payload>[] payload() default {};
    Class<?>[] groups() default {};

}
