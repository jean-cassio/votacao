package com.example.votacao.mappers;

import com.example.votacao.dto.VotoPautaRequest;
import com.example.votacao.model.Pauta;
import com.example.votacao.model.VotoPauta;

public class VotoPautaMapper {

    public static VotoPauta mappearVotoPauta(VotoPautaRequest votoPautaRequest, Pauta pauta){

        return VotoPauta.builder()
                .voto(VotoMapper.mappearVoto(votoPautaRequest.voto()))
                .pauta(pauta)
                .build();
    }
}
