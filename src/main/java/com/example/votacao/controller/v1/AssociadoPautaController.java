package com.example.votacao.controller.v1;

import com.example.votacao.dto.AssociadoPautaRequest;
import com.example.votacao.dto.AssociadoPautaResponse;
import com.example.votacao.model.AssociadoPauta;
import com.example.votacao.service.AssociadoPautaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/associado/pauta")
@Validated
public class AssociadoPautaController {

    private final AssociadoPautaService associadoPautaService;

    public AssociadoPautaController(AssociadoPautaService associadoPautaService){
        this.associadoPautaService = associadoPautaService;
    }

    @PostMapping
    public ResponseEntity<AssociadoPautaResponse> cadastrarAssociadoNaPauta(@RequestBody @Valid AssociadoPautaRequest associadoPautaRequest){

        AssociadoPauta associadoPauta = associadoPautaService.cadastarAssociadoNaPauta(associadoPautaRequest);

        return new ResponseEntity<>(new AssociadoPautaResponse(associadoPauta), HttpStatus.CREATED);
    }

}
