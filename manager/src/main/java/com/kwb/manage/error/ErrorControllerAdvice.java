package com.kwb.manage.error;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages = "com.kwb.manage.controller")
public class ErrorControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<?> handlerControllerException(HttpServletRequest request, Throwable throwable) {
        HttpStatus status = null;
        Map<String, Object> attrs = new HashMap<String, Object>();
        String errorCode = throwable.getMessage();
        ErrorEnum errorEnum = ErrorEnum.getByCode(errorCode);
        attrs.put("message",errorEnum.getMessage());
        attrs.put("code", errorEnum.getCode());
        attrs.put("cantry",errorEnum.isCantry());
        attrs.put("type", "advice");
        Assert.isNull(attrs,"advice");
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        if (null == statusCode) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }else{
            status = HttpStatus.valueOf(statusCode);
        }
        if (null == statusCode) {
            return new ResponseEntity<>(attrs, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(attrs, HttpStatus.valueOf(statusCode));
        }
    }
}
