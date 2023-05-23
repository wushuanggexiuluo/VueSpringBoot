package com.scm.myblog.common.ValidatedLancer;

import com.scm.myblog.common.ValidatedLancer.MyAnnotate.OneOf;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class OneOfValidator implements ConstraintValidator<OneOf, String> {

    private List<String> list;
    // constraintAnnotation是改注解定义的所有允许的值
    @Override
    public void initialize(OneOf constraintAnnotation) {
        list= Arrays.asList(constraintAnnotation.value());
    }

    // s是从注解位置传递过来的选择的属性的值
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }
        return list.contains(s);
    }
}
