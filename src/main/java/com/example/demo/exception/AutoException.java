package com.example.demo.exception;

/**
 * 自定义异常 配置
 * fzh
 */
public class AutoException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    String errorCode;
    String message;


    public AutoException()
    {

    }

    public AutoException(String message) {
        super(message);
    }


    public AutoException(String message, Throwable cause) {
        super(message, cause);
    }


    public AutoException(Throwable cause) {
        super(cause);
    }


    protected AutoException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
