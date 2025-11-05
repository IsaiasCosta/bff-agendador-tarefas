package com.isaiascosta.bffagendadortarefas.infrastructure.exceptions;

public class BusinessException extends RuntimeException {
   public BusinessException(String message) {
      super(message);
   }
   public BusinessException(String message, Throwable throwable) {
      super(message,throwable);
   }
}
