package com.yfh.common.exception;

public abstract class BaseExceptionBuilder {

    public static BaseException build(Class<? extends BaseException> clz, String errorMessage) {
        return null;
    }

    public static BaseException build(Class<? extends BaseException> clz, String errorCode, String errorMessage) {
        return null;
    }

    public static BaseException build(Class<? extends BaseException> clz, String errorMessage, Throwable e) {
        return null;
    }

    public static BaseException build(Class<? extends BaseException>clz, String errorCode, String errorMessage, Throwable e) {
        return null;
    }


}
