package com.example.votacao.controller.v1;

import com.example.votacao.dto.PautaAtualizarRequest;
import com.example.votacao.dto.PautaRequest;
import com.example.votacao.dto.PautaResponse;
import com.example.votacao.mappers.PautaMapper;
import com.example.votacao.model.Pauta;
import com.example.votacao.service.PautaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/pauta")
@Validated
public class PautaController {

    private final PautaService pautaService;
    
    public PautaController(PautaService pautaService){
        this.pautaService = pautaService;
    }

    @PostMapping
    public ResponseEntity<PautaResponse> cadastrarPauta(@RequestBody @Valid PautaRequest pautaRequest){

        Pauta pauta = pautaService.cadastrarPauta(PautaMapper.mapearPauta(pautaRequest));

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<Page<PautaResponse>> listarPautas(@PageableDefault(size = 10, sort = {"id"}) Pageable pageable){

        Page<Pauta> pautas = pautaService.listarPautas(pageable);

        Page<PautaResponse> pautasResponse = pautas.map(PautaResponse::new);

        return new ResponseEntity<>(pautasResponse, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<PautaResponse> atualizarPauta(@RequestBody @Valid PautaAtualizarRequest pautaAtualizarRequest){

        Pauta pautaAtualizada = pautaService.atualizarPauta(PautaMapper.mapearPauta(pautaAtualizarRequest));

        return new ResponseEntity<>(new PautaResponse(pautaAtualizada), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PautaResponse> buscarPauta(@PathVariable("id") String id){

        Pauta pauta = pautaService.buscarPautaPorID(id);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<PautaResponse> deletarPauta(@PathVariable("id") String id){

        Pauta pauta = pautaService.deletarPauta(id);

        return new ResponseEntity<>(new PautaResponse(pauta), HttpStatus.OK);
    }

}
