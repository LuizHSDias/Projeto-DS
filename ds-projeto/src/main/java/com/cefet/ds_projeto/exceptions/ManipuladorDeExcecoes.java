package com.cefet.ds_projeto.exceptions;

import java.time.Instant;
import java.util.List; 
import java.util.stream.Collectors; 

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError; 
import org.springframework.web.bind.MethodArgumentNotValidException; 
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ManipuladorDeExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErroResposta> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND; 
        ErroResposta erro = new ErroResposta();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Recurso não encontrado");
        erro.setMessage(e.getMessage()); 
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErroResposta> illegalArgument(IllegalArgumentException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST; 
        ErroResposta erro = new ErroResposta();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Requisição inválida");
        erro.setMessage(e.getMessage()); 
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> validationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; 
                                                              
        ErroResposta erro = new ErroResposta();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Erro de validação");
        
        List<String> fieldErrors = e.getBindingResult().getFieldErrors().stream()
            .map(FieldError::getDefaultMessage) 
            .collect(Collectors.toList());
        
        erro.setMessage("Um ou mais campos estão inválidos: " + String.join("; ", fieldErrors)); // Concatena as mensagens
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleGenericException(Exception e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500
        ErroResposta erro = new ErroResposta();
        erro.setTimestamp(Instant.now());
        erro.setStatus(status.value());
        erro.setError("Erro interno do servidor");
        erro.setMessage("Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.");
        erro.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(erro);
    }
}