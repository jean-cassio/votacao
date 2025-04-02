package com.example.votacao.mappers;

import com.example.votacao.dto.VotoRequest;
import com.example.votacao.model.Voto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class VotoMapper {

    public static Voto mappearVoto(VotoRequest votoRequest){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return Voto.builder()
                .voto(votoRequest.voto())
                .dataHoraVoto(LocalDateTime.parse(votoRequest.dataHoraVoto(), dateTimeFormatter))
                .build();
    }
}
