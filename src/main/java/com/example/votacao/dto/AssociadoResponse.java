package com.example.votacao.dto;


import com.example.votacao.model.Associado;

public record AssociadoResponse(
        String cpf
) {

    public AssociadoResponse(Associado associado){
        this(associado.getCPF());
    }
}
