package com.yfh.common.error;


import java.nio.file.AccessDeniedException;

import javax.validation.ConstraintViolationException;

import com.yfh.common.exception.BaseException;
import com.yfh.common.exception.BizException;
import com.yfh.common.resp.Response;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;


@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {
   

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class })
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response handleMethodArgumentNotValidException(Exception exception) {
        StringBuilder errorInfo = new StringBuilder();
        BindingResult bindingResult = null;
        if (exception instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) exception).getBindingResult();
        }
        if (exception instanceof BindException) {
            bindingResult = ((BindException) exception).getBindingResult();
        }
        for (int i = 0; i < bindingResult.getFieldErrors().size(); i++) {
            if (i > 0) {
                errorInfo.append(",");
            }
            FieldError fieldError = bindingResult.getFieldErrors().get(i);
            errorInfo.append(fieldError.getField()).append(" :").append(fieldError.getDefaultMessage());
        }
        log.error(errorInfo.toString());
        // 这里返回自己的Result的结果类。
        return Response.buildFailure(ErrorCode.ERROR_CODE_PARAM_VALIDATED_FAILED, errorInfo.toString());
    }

  @ExceptionHandler({ ConstraintViolationException.class })
  @ResponseStatus(HttpStatus.OK)
  public Response handleException(ConstraintViolationException e) {
    log.error("Runtime Info: ", e);
    return Response.buildFailure(ErrorCode.ERROR_CODE_PARAM_VALIDATED_FAILED, e.getMessage());
  }

 
  @ExceptionHandler(BaseException.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Response handleException(BaseException e) {
    log.error(e.toString());
    return Response.buildFailure(e.getErrCode(), e.getMessage());
  }

  @ExceptionHandler(value = AccessDeniedException.class)
  @ResponseStatus(HttpStatus.OK)
  public Response handleException(AccessDeniedException e) {
    log.error("Runtime Info: ", e);
    return Response.buildFailure(ErrorCode.ERROR_CODE_SECURITY_ACCESS_DENIED, e.getMessage());
  }

  @ExceptionHandler(value = BizException.class)
  @ResponseStatus(HttpStatus.OK)
  public Response handleException(BizException e){
    log.error("Runtime Info: ", e);
    return Response.buildFailure(ErrorCode.ERROR_CODE_BUSINESS_GENERAL_FAILED, e.getMessage());
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  public Response handleException(Exception e) {
    log.error(e.toString());
     //这里返回自己的Result的结果类。
    return Response.buildFailure(ErrorCode.ERROR_CODE_APPLICATION_GENERAL_FAILED, e.getMessage());
  }

}
