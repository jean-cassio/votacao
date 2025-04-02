package com.example.votacao.dto;

import com.example.votacao.enums.EnumVoto;
import com.example.votacao.model.Voto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.format.DateTimeFormatter;

public record VotoRequest(
        @NotNull(message = "voto não pode ser nulo")
        EnumVoto voto,

        @NotBlank(message = "data não pode estar em branco")
        String dataHoraVoto
) {
        private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        public VotoRequest(Voto voto){
                this(voto.getVoto(), voto.getDataHoraVoto().format(dateTimeFormatter));
        }
}
