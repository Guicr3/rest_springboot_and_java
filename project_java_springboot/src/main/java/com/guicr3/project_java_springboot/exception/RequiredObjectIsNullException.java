package com.guicr3.project_java_springboot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredObjectIsNullException extends RuntimeException {
    public RequiredObjectIsNullException() {
        super("Not Allowed to persist a null object! ");
    }

    public RequiredObjectIsNullException(String message) {
        super(message);
    }
}
