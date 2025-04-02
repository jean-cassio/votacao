package com.example.votacao.dto;

import com.example.votacao.model.Pauta;
import java.time.format.DateTimeFormatter;

public record PautaResponse(

        String id,

        String titulo,

        String descricao,

        String inicioSessao,

        String finalSessao

) {

    public PautaResponse(Pauta pauta){
        this(
                pauta.getId(),
                pauta.getTitulo(),
                pauta.getDescricao(),
                pauta.getSessao().getInicioSessao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                pauta.getSessao().getFinalSessao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
        );
    }
}
