package com.example.riad.doctorsappointment.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    // without this method spring handle the error and send 500 response error; but it should be 404. this
    // method does that!
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> springHandleNotFound(Exception ex) throws IOException{
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customErrorResponse.setError(ex.getMessage());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }
}
