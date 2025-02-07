package com.grademanagement.grade_management_system.validation;



import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ScoreValidator implements ConstraintValidator<Score, String> {

    List<String> scores = Arrays.asList(
            "A+", "A", "A-",
            "B+", "B", "B-",
            "C+", "C", "C-",
            "D+", "D", "D-",
            "F"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        for (String string : scores) {
            if (value.equals(string)) return true;
        }
        return false;
    }

}