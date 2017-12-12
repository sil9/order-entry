package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.constant.ResponseMessage;
import com.netcracker.selyutin.entity.ExceptionInfo;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import com.netcracker.selyutin.exception.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionInfo> handleEntityNotFoundException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ExceptionInfo> handleClientException(HttpServletRequest request, ResponseException e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString());
        if (e.getStatusCode().equals((HttpStatus.BAD_REQUEST))) {
            exceptionInfo.setMessage(ResponseMessage.BAD_REQUEST);
            return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
        } else {
            exceptionInfo.setMessage(ResponseMessage.SERVER_ERROR);
            return new ResponseEntity<>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionInfo> handleRequestException(HttpServletRequest request) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), ResponseMessage.BAD_REQUEST);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConnectException.class)
    public ResponseEntity<ExceptionInfo> handleConnectException(HttpServletRequest request) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), ResponseMessage.SERVER_ERROR);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({UnsupportedOperationException.class, IllegalArgumentException.class})
    public ResponseEntity<ExceptionInfo> handleUnsupportedOperationException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

}
