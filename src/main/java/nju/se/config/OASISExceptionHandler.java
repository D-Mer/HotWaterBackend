package nju.se.config;


import nju.se.exception.UserException;
import nju.se.vo.Response;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author jh
 * @date 2021/2/17
 */
@RestControllerAdvice
public class OASISExceptionHandler {


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(UserException.class)
    public Response handleUserException(UserException ex) {
        String msg = ex.getMessage();
        return Response.buildFailure(ex.getCode(), msg);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        String msg = ex.getMessage();
        Set<HttpMethod> methods = ex.getSupportedHttpMethods();
        List<String> supported = new LinkedList<>();
        if (methods != null) {
            for (HttpMethod httpMethod : methods) {
                supported.add(httpMethod.name());
            }
        }
        return Response.buildFailure(msg + ". try: " + supported);
    }


    //全局未知错误
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception ex) {
        System.out.println(ex.getMessage());
        return Response.buildFailure(ex.getMessage());
    }

}
