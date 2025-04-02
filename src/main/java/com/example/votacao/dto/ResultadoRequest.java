package com.example.votacao.dto;

import jakarta.validation.constraints.NotNull;

public record ResultadoRequest(
        @NotNull(message = "deve ser informada o idPauta")
        String idPauta
) {}
