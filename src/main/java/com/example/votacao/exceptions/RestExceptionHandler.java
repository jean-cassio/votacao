package com.example.votacao.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler   {
    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(NotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .titulo("Not Found Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(ConflictException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.CONFLICT.value())
                        .titulo("Conflict Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<ExceptionDTO> handlerNotFoundException(NoContentException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(
                ExceptionDTO.builder()
                        .dataHora(LocalDateTime.now())
                        .status(HttpStatus.NO_CONTENT.value())
                        .titulo("No Content Exception")
                        .detalhe(exception.getMessage())
                        .mensagem("Cheque a documentação da API")
                        .build(),
                HttpStatus.NO_CONTENT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        log.error(exception.getMessage());
        // Extrair erros de validação do objeto BindException
        BindingResult result = exception.getBindingResult();
        List<String> errors = result.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        // Retornar a lista de erros ou um objeto com a estrutura desejada
        return ResponseEntity.badRequest().body(errors);
    }
}
