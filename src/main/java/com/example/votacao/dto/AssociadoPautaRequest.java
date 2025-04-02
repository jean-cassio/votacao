package com.example.votacao.dto;


import com.example.votacao.model.AssociadoPauta;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record AssociadoPautaRequest (
        String idPauta,

        @NotBlank(message = "cpf não pode estar em branco")
        @CPF(message = "cpf invalido")
        String cpf
) {
        public AssociadoPautaRequest(AssociadoPauta associadoPauta){
                this(associadoPauta.getPauta().getId(), associadoPauta.getAssociado().getCPF());
        }
}
