package com.dianping.rotate.shop.exception;

import com.dianping.rotate.shop.model.ServiceResult;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

/**
 * Created by zaza on 15/2/2.
 */
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper jsonMapper;
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleCustomerInfoWebExceptions(Exception ex,WebRequest request) {
        HttpStatus status;
        HttpHeaders headers = new HttpHeaders();
        String message = "";
        logger.error(ex.getMessage(), ex);
        if(false){
            //todo:处理自定义的异常
        }else{
            status = HttpStatus.OK;
            ServiceResult result = new ServiceResult();
            result.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.setMsg("服务器出现错误:"+ex.getMessage());
            headers.add("Content-Type", "application/json;charset=UTF-8");
            try {
                message = jsonMapper.writeValueAsString(result);
            } catch (Exception e) {}
        }
        String bodyOfResponse = message;
        return handleExceptionInternal(ex, bodyOfResponse, headers, status,request);
    }


}
