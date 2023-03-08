package com.scm.myblog.exception;

public class SystemException extends RuntimeException{

    private Integer errorCode;
    private String errorMsg;


    public SystemException() {
    }

    public SystemException(Integer errorCode, String errorMsg) {
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
