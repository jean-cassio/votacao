package com.example.votacao.exceptions;

public class ConflictException extends RuntimeException{
    public ConflictException(String mensagem) {
        super(mensagem);
    }
}
