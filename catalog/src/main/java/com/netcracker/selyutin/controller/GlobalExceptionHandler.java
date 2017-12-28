package com.netcracker.selyutin.controller;

import com.netcracker.selyutin.constant.ExceptionMessage;
import com.netcracker.selyutin.constant.LoggerConstant;
import com.netcracker.selyutin.entity.ExceptionInfo;
import com.netcracker.selyutin.exception.EntityNotFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionInfo> handleEntityNotFoundException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        LOGGER.error(LoggerConstant.TRANSACTION_FAILED);
        LOGGER.error(e);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ExceptionInfo> handleRequestException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), ExceptionMessage.BAD_REQUEST);
        LOGGER.error(e);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionInfo> handleIllegalArgumentException(HttpServletRequest request, Exception e) {
        ExceptionInfo exceptionInfo = new ExceptionInfo(request.getRequestURL().toString(), e.getMessage());
        LOGGER.error(e);
        return new ResponseEntity<>(exceptionInfo, HttpStatus.BAD_REQUEST);
    }

}
