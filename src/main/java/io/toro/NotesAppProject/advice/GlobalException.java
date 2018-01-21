package io.toro.NotesAppProject.advice;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.toro.NotesAppProject.pojo.ApiException;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = NumberFormatException.class)
    private ResponseEntity<Object> numberFormatException(NumberFormatException ex, HttpServletRequest httpServletRequest,WebRequest webRequest) {
        String msg = "Bad Request / Invalid or missing parameter";
        ApiException exceptionResponse = getExceptionResponse( ex, httpServletRequest,msg,HttpStatus.BAD_REQUEST );
        return handleExceptionInternal(ex,exceptionResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = RuntimeException.class)
    private ResponseEntity<Object> runTimeException(RuntimeException ex, HttpServletRequest httpServletRequest,WebRequest webRequest) {
        String msg = "Server error";
        ApiException exceptionResponse = getExceptionResponse( ex, httpServletRequest,msg,HttpStatus.INTERNAL_SERVER_ERROR );
        return handleExceptionInternal(ex,exceptionResponse,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, webRequest);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    private ResponseEntity<Object> constraintViolationException(ConstraintViolationException ex, HttpServletRequest httpServletRequest,WebRequest webRequest) {
        String msg = "Bad Request: required field might not be filled";
        ApiException exceptionResponse = getExceptionResponse( ex, httpServletRequest,msg,HttpStatus.BAD_REQUEST );
        return handleExceptionInternal(ex,exceptionResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    private ResponseEntity<Object> transactionSystemException(TransactionSystemException ex, HttpServletRequest httpServletRequest,WebRequest webRequest) {
        String msg = "Bad Request: required field might not be filled";
        ApiException exceptionResponse = getExceptionResponse( ex, httpServletRequest,msg,HttpStatus.BAD_REQUEST );
        return handleExceptionInternal(ex,exceptionResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex, HttpServletRequest httpServletRequest,WebRequest webRequest) {
        String msg = "Bad Request / Invalid or missing parameter";
        ApiException exceptionResponse = getExceptionResponse( ex, httpServletRequest,msg,HttpStatus.BAD_REQUEST );
        return handleExceptionInternal(ex,exceptionResponse,new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }


    private ApiException getExceptionResponse( Exception ex, HttpServletRequest httpServletRequest, String msg, HttpStatus status ) {
        return new ApiException( status.value(),
                httpServletRequest.getRequestURI(),
                httpServletRequest.getMethod(),
                ex.getClass().toString(),
                msg,
                new Date().toString());
    }

}
