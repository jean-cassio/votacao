package com.example.votacao.service.interfaces;

import com.example.votacao.model.Associado;

public interface IAssociadoService {
    Associado criarAssociado(Associado associado);
    Associado buscarPorCPF(String CPF);

}
