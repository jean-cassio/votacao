package com.example.votacao.controller.v1;

import com.example.votacao.dto.AssociadoRequest;
import com.example.votacao.dto.AssociadoResponse;
import com.example.votacao.mappers.AssociadoMapper;
import com.example.votacao.model.Associado;
import com.example.votacao.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/associado")

public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping
    public ResponseEntity<AssociadoResponse> cadastrarAssociado(@RequestBody @Valid AssociadoRequest associadoRequest) {

        Associado associado = associadoService.criarAssociado(AssociadoMapper.mappearAssociado(associadoRequest));

        return new ResponseEntity<>(new AssociadoResponse(associado), HttpStatus.CREATED);
    }

}
