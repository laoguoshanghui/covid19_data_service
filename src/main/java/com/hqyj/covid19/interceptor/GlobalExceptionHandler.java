package com.hqyj.covid19.interceptor;

import com.hqyj.covid19.dto.ResponseDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseDTO<String> handlerException(Exception e, HttpServletRequest request){
        ResponseDTO<String> responseDTO = new ResponseDTO<>();
        responseDTO.setCode(500);
        return responseDTO;
    }

}
