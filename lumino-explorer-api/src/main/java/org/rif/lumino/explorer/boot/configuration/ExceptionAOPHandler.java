package org.rif.lumino.explorer.boot.configuration;

import org.rif.lumino.explorer.exceptions.ApiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAOPHandler {
    @ResponseBody
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<?> onApiException(ApiException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMessage());
    }
}
