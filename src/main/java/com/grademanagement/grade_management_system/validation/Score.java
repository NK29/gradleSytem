package com.grademanagement.grade_management_system.validation;




import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ScoreValidator.class)

public @interface Score {

    String message() default "The score is not valid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}

