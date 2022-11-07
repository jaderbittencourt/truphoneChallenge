package com.truphone.jaderbittencourt.truphoneChallenge.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PatchDeviceValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface PatchDeviceAnnotation {
    String message() default "{error.device}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
