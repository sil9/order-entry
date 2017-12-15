package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.entity.ExceptionInfo;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionInfo> handleEntityNotFoundException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionInfo> handleRequestException(HttpServletRequest request) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), ExceptionMessage.BAD_REQUEST);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionInfo> handleUnsupportedOperationException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ExceptionInfo> handleException(HttpServletRequest request, Exception e) {
//        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), ExceptionMessage.SERVER_ERROR);
//        return new ResponseEntity<>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
