package com.scm.myblog.myannotation;

import com.scm.myblog.myvalidator.RegxValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
//约束注解应用的目标元素类型
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//约束注解应用的时机 (runtime是运行时)
@Retention(RUNTIME)
////使得EnumString可以重复使用
//@Repeatable(EnumString.class)
//Documented注解是javaAPI中的四个元注解之一，只用于修饰注解，用于显示提示信息
@Documented
//标明由哪个类执行校验逻辑
@Constraint(validatedBy = RegxValidator.class)
public @interface EnumString {
    //定义异常信息
    String message() default "老铁错了奥（@-.-@）";

    //约束注解在验证时所属的组别
    Class<?>[] groups() default {};

    // 约束注解的有效负载
    Class<? extends Payload>[] payload() default {};


    // 传过来的值
    String value();

//    /**
//     * Defines several {@link EnumString} annotations on the same element.
//     *
//     * @see EnumString
//     */
//    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
//    @Retention(RUNTIME)
//    @Documented
//    @interface List {
//        EnumString[] value();
//    }
}