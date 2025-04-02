package com.example.votacao.controller.v1;

import com.example.votacao.dto.ResultadoRequest;
import com.example.votacao.dto.ResultadoResponse;
import com.example.votacao.dto.VotoPautaRequest;
import com.example.votacao.dto.VotoPautaResponse;
import com.example.votacao.mappers.VotoPautaMapper;
import com.example.votacao.model.Pauta;
import com.example.votacao.service.PautaService;
import com.example.votacao.service.VotoService;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/voto")
@Validated
public class VotoPautaController {

    private final VotoService votoService;
    private final PautaService pautaService;
    

    public VotoPautaController(VotoService votoService, PautaService pautaService){
        this.votoService = votoService;
        this.pautaService = pautaService;        
    }

    @PostMapping
    public ResponseEntity<VotoPautaResponse> cadastrarVoto(@RequestBody @Valid VotoPautaRequest votoPautaRequest){

        Pauta pauta = pautaService.buscarPautaPorID(votoPautaRequest.idPauta());

        votoService.cadastrarVoto(VotoPautaMapper.mappearVotoPauta(votoPautaRequest, pauta), votoPautaRequest.cpf());

        return new ResponseEntity<>(new VotoPautaResponse(pauta.getId(), votoPautaRequest.cpf()), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResultadoResponse> resultadoVotacao(@PathParam("id") @Valid ResultadoRequest resultadoRequest){

        Pauta pauta = pautaService.buscarPautaPorID(resultadoRequest.idPauta());

        ResultadoResponse resultado = votoService.resultadoVotacao(pauta);

        return new ResponseEntity<>(resultado, HttpStatus.OK);
    }
}
