package com.example.votacao.service.interfaces;

import com.example.votacao.model.Pauta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPautaService {
    Pauta cadastrarPauta(Pauta pauta);

    Pauta buscarPautaPorID(@NotNull String id);

    Page<Pauta> listarPautas(Pageable pageable);

    Pauta deletarPauta(String id);

    Pauta atualizarPauta(Pauta pauta);
}
