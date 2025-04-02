package com.example.votacao.mappers;

import com.example.votacao.dto.SessaoRequest;
import com.example.votacao.model.Sessao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessaoMapper {

    public static Sessao mapearSessao(SessaoRequest sessaoRequest){

        Sessao sessao = new Sessao();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        sessao.setInicioSessao(LocalDateTime.parse(sessaoRequest.inicioSessao(), dateTimeFormatter));
        sessao.setFinalSessao(LocalDateTime.parse(sessaoRequest.finalSessao(), dateTimeFormatter));

        return sessao;
    }
}
