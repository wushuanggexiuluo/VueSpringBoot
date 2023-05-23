package com.scm.myblog.entity.VO;

import com.scm.myblog.common.ExceptionLancer.CommonException;
import com.scm.myblog.entity.CORE.Code;
import com.scm.myblog.entity.CORE.Tips;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "统一返回返回结果对象")
public class Result {
    @ApiModelProperty(value = "后台返回的数据")
    Object data;
    @ApiModelProperty(value = "返回状态码")
    String code;
    @ApiModelProperty(value = "返回提示信息")
    String message;

    //自定义异常返回的结果
    public static Result defineError(CommonException de){
        Result result = new Result();
        result.setCode(de.getErrorResult().getCode());
        result.setMessage(de.getErrorResult().getMsg());
        result.setData(null);
        return result;
    }
    //系统异常返回结果
    public static Result systemError(Exception e)
    {
        return new Result(null,Code.SYSTEM_ERR, Tips.SYSTEM_ERR);
    }
}
