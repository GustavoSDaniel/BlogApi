package com.devgustavosdaniel.apiblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(MyException.class)
   // public ResponseEntity<Object> myException(MyException exception) {
    //     Map<String, Object> body = new HashMap<>();
    //     body.put("timestamp", LocalDateTime.now());
    //     body.put("status", HttpStatus.NOT_FOUND.value());
    //    body.put("error", "Recurso não encontrado");
    //    body.put("message", exception.getMessage());
    //   return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

    public ResponseEntity<ApiError> MyException(MyException exception) {
       ApiError error = new ApiError(
               LocalDateTime.now(),
               HttpStatus.NOT_FOUND.value(),
               "Recurso não encontrado",
               exception.getMessage()
       );
       return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
   }

    }

