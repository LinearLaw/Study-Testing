package com.linear.exception;

/**
 * 58.6、自定义的异常类
 */
public class SysException extends Exception {

    private String message;
    public SysException(String message){ this.message = message; }

    @Override
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
