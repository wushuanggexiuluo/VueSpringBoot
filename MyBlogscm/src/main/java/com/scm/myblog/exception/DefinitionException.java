package com.scm.myblog.exception;


/**
 * 定义异常
 *
 * @author Lancer
 * @date 2022/12/04
 */
public class DefinitionException extends RuntimeException{
    private Integer errorCode;
    private String errorMsg;


    public DefinitionException() {
    }

    public DefinitionException(Integer errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    /**
     * 获取
     * @return errorCode
     */
    public Integer getErrorCode() {
        return errorCode;
    }

    /**
     * 设置
     * @param errorCode
     */
    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * 获取
     * @return errorMsg
     */
    public String getErrorMsg() {
        return errorMsg;
    }

    /**
     * 设置
     * @param errorMsg
     */
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String toString() {
        return "DefinitionException{errorCode = " + errorCode + ", errorMsg = " + errorMsg + "}";
    }
}
