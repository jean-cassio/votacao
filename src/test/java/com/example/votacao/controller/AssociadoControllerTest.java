package com.example.votacao.controller;

import com.example.votacao.controller.v1.AssociadoController;
import com.example.votacao.dto.AssociadoRequest;
import com.example.votacao.exceptions.ConflictException;
import com.example.votacao.model.Associado;
import com.example.votacao.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest {

    @MockBean
    private AssociadoService associadoService;

    @Autowired
    MockMvc mockMvc;

    public static final String PATH = "/v1/associado";

    @BeforeEach
    void init() {

    }

    @Test
    void deveCadastrarAssociadoSucesso() throws Exception {

        Associado associado = new Associado();

        associado.setCPF("013.809.290-75");

        Mockito.when(associadoService.criarAssociado(Mockito.any())).thenReturn(associado);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoRequest("013.809.290-75"));

        this.mockMvc.perform(post(PATH)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveRetornarConflictExceptionAoTentarCadastrarAssociadoJaCadastrado() throws Exception {

        Associado associado = new Associado();

        associado.setCPF("013.809.290-75");

        Mockito.when(associadoService.criarAssociado(Mockito.any())).thenThrow(ConflictException.class);

        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(new AssociadoRequest("013.809.290-75"));

        this.mockMvc.perform(post(PATH)
                .content(json)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }
}