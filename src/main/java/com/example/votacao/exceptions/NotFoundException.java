package com.example.votacao.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String mensagem) {
        super(mensagem);
    }
}
