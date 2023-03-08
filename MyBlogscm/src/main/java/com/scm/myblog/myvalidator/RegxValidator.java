package com.scm.myblog.myvalidator;

import com.scm.myblog.entity.Code;
import com.scm.myblog.entity.Regx;
import com.scm.myblog.entity.Tips;
import com.scm.myblog.exception.DefinitionException;
import com.scm.myblog.myannotation.EnumString;
import com.scm.myblog.utils.RegxUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class RegxValidator implements ConstraintValidator<EnumString, String> {
    //获取自己定义的校验规则
    private String enumString;

    @Override
    public void initialize(EnumString constraintAnnotation) {
        enumString = constraintAnnotation.value();
    }

    // value是校验字段的值
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (getResult(value)){
            return true;
        }
        else {
            throw new DefinitionException(Code.Parms_Err,enumString+ Tips.Parms_Err);
        }
    }

    //获取校验结果的返回值
    public boolean getResult(String value){
        boolean isOk;
        if(Regx.getEmail.equals(enumString)) {
            isOk=RegxUtils.regxEmail(value);
        }else if(Regx.getInternet.equals(enumString)){
            isOk=RegxUtils.regxUrl(value);
        }else if (Regx.getIp.equals(enumString)){
            isOk=RegxUtils.regxIp(value);
        }
        else {
            isOk = false;
        }
        return isOk;
    }
}