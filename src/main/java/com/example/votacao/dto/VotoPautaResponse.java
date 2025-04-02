package com.example.votacao.dto;


import com.example.votacao.model.VotoPauta;

public record VotoPautaResponse(

        String idPauta,

        String cpf

) {

    public VotoPautaResponse(VotoPauta votoPauta, String cpf){
        this(votoPauta.getPauta().getId(), cpf);
    }
}
