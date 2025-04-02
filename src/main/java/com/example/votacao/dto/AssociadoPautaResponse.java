package com.example.votacao.dto;

import com.example.votacao.model.AssociadoPauta;

public record AssociadoPautaResponse(
        String idPauta,

        String titulo,

        String cpf,

        Boolean votou
) {

    public AssociadoPautaResponse(AssociadoPauta associadoPauta){
        this(
                associadoPauta.getPauta().getId(),
                associadoPauta.getPauta().getTitulo(),
                associadoPauta.getAssociado().getCPF(),
                associadoPauta.getVotou()
        );
    }
}
