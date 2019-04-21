package com.css.bdpfnew.web.exception;

import com.css.bdpfnew.Message;
import lombok.extern.apachecommons.CommonsLog;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常处理器®
 */
@CommonsLog
@ControllerAdvice
@ResponseBody
class ExceptionHandlerControllerAdvice {

    /**
     * 用于处理通用异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Message bindException(MethodArgumentNotValidException e) {
        StringBuffer buffer = new StringBuffer("参数校验错误：");
        BindingResult result = e.getBindingResult();
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                buffer.append(error.getDefaultMessage()+";");
            }
        }
        String errorMesssage = "校验失败:";

        for (FieldError fieldError : result.getFieldErrors()) {
            errorMesssage += fieldError.getDefaultMessage() + ", ";
        }
        System.out.println(errorMesssage);

        return Message.error(buffer.toString(),Message.ERROR_CODE_PARAM);
    }


    /********************************** HELPER METHOD **********************************/
    private void logError(HttpServletRequest request, Exception e) {
        log.error("[URI: " + request.getRequestURI() + "]"
                + "[error: " + e.getMessage() + "]");
    }

}
