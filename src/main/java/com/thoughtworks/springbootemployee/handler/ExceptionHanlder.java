package com.thoughtworks.springbootemployee.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHanlder {

    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String notFound() {
        return "资源没找到";
    }
    @ExceptionHandler(IllegalAccessError.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String illegalOperate() {
        return "非法操作";
    }

}
