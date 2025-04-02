package com.example.votacao.exceptions;

public class NoContentException extends RuntimeException{
    public NoContentException(String mensagem) {
        super(mensagem);
    }
}
