package com.isaiascosta.bffagendadortarefas.infrastructure.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.IllegalArgumentException;

@ControllerAdvice
public class GlobalExceptionHandler {

   @ExceptionHandler(ResourceNotFoundExecption.class)
   public ResponseEntity<String> handleResourceNotFoundExecption(ResourceNotFoundExecption ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);

   }

   @ExceptionHandler(ConflictException.class)
   public ResponseEntity<String> handleConflicExecption(ConflictException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
   }
   @ExceptionHandler(UnauthorizedException.class)
   public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException ex) {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
   }
   @ExceptionHandler(java.lang.IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex){
      return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
   }
}
