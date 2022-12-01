package com.farhan.auth.service.error;

import com.farhan.auth.service.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestEntityErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorMessage> EmailAlreadyExistsException(EmailAlreadyExistsException e, WebRequest request){
        ErrorMessage errorMessage = ErrorMessage.builder().status(HttpStatus.NOT_ACCEPTABLE).message(e.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorMessage);
    }


}
