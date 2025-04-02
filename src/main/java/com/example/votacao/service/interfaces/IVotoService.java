package com.example.votacao.service.interfaces;


import com.example.votacao.model.VotoPauta;

public interface IVotoService {
    VotoPauta cadastrarVoto(VotoPauta votoPauta, String cpf);

}
