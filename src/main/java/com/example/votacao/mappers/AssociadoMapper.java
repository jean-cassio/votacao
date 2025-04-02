package com.example.votacao.mappers;


import com.example.votacao.dto.AssociadoRequest;
import com.example.votacao.model.Associado;

public class AssociadoMapper {

    public static Associado mappearAssociado(AssociadoRequest associadoRequest){

        Associado associado = new Associado();

        associado.setCPF(associadoRequest.cpf());

        return associado;
    }
}
