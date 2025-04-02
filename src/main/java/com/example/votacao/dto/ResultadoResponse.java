package com.example.votacao.dto;

public record ResultadoResponse(
        Integer votosPositivos,
        Integer votosNegativos
) {}
