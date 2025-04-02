package com.example.votacao.service.interfaces;


import com.example.votacao.dto.AssociadoPautaRequest;
import com.example.votacao.model.AssociadoPauta;

public interface IAssociadoPautaService {
    AssociadoPauta cadastarAssociadoNaPauta(AssociadoPautaRequest associadoPautaRequest);
}
