package com.guicr3.project_java_springboot.exception;

import java.util.Date;

public record ExceptionResponse(Date timeStamp, String message, String details) {}
