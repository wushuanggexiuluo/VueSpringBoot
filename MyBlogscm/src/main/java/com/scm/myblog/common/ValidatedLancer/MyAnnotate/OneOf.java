package com.scm.myblog.common.ValidatedLancer.MyAnnotate;

import com.scm.myblog.common.ValidatedLancer.OneOfValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


//路径参数和没有封装为实体的 RequestParam 参数，
// 首先需要在参数前面加上校验注解，然后需要在 Controller 类上面加上注解 @Validated 才会生效


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = OneOfValidator.class)
public @interface OneOf {

    String message() default "只能从备选值中选择";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] value();
}