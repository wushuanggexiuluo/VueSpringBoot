package com.scm.myblog.common.ErrResult;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResult  implements Serializable {
    private static final long serialVersionUID = -8738363760223125457L;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误消息
     */
    private String msg;


    public static ErrorResult build(ErrorResult commonErrorResult, String otherMsg) {

        return new ErrorResult(commonErrorResult.getCode(), commonErrorResult.getMsg() + ":" + otherMsg);
    }

    public static ErrorResult build(ErrorResult commonErrorResult) {

        return new ErrorResult(commonErrorResult.getCode(), commonErrorResult.getMsg());
    }
}
