package com.yfh.common.error;

public interface ErrorCode {
    
    public static final String ERROR_CODE_APPLICATION_GENERAL_FAILED = "500";
    public static final String ERROR_CODE_SECURITY_ACCESS_DENIED = "403";

    public static final String ERROR_CODE_PARAM_VALIDATED_FAILED = "8001";
    public static final String ERROR_CODE_EXISTED_LOGIN_NAME_FAILED = "8002";
    public static final String ERROR_CODE_NO_PERMISSION_FAILED = "8003";

    public static final String ERROR_CODE_BUSINESS_GENERAL_FAILED = "9001";
}
