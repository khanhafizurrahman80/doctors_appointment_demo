package com.example.riad.doctorsappointment.web.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDateTime;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setMessage(ex.getMessage());
        customErrorResponse.setDetails(request.getDescription(false));
        customErrorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // without this method spring handle the error and send 500 response error; but it should be 404. this
    // method does that!
    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> springHandleNotFound(RecordNotFoundException ex, WebRequest request) throws IOException{
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        customErrorResponse.setMessage(ex.getMessage());
        customErrorResponse.setDetails(request.getDescription(false));
        return new ResponseEntity<>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse();
        customErrorResponse.setLocalDateTime(LocalDateTime.now());
        customErrorResponse.setMessage("Validation Failed");
        customErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        customErrorResponse.setDetails(ex.getBindingResult().toString());
        return new ResponseEntity(customErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
