package com.netcracker.selyutin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class ResponseException extends HttpStatusCodeException {

    public ResponseException(HttpStatus statusCode) {
        super(statusCode);
    }
}
