package com.example.votacao.controller;

import com.example.votacao.controller.v1.VotoPautaController;
import com.example.votacao.dto.*;
import com.example.votacao.enums.EnumVoto;
import com.example.votacao.exceptions.ConflictException;
import com.example.votacao.exceptions.NotFoundException;
import com.example.votacao.model.*;
import com.example.votacao.service.PautaService;
import com.example.votacao.service.VotoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(VotoPautaController.class)
class VotoControllerTest {

        @MockBean
        private VotoService votoService;

        @MockBean
        private PautaService pautaService;

        @Autowired
        private MockMvc mockMvc;

        @Mock
        private Pauta pauta;

        @Mock
        private Sessao sessao;

        @Mock
        private Voto voto;

        @Mock
        private Voto votoForaHorario;

        @Mock
        private VotoPauta votoPauta;

        @Mock
        private Associado associado;

        @Mock
        private Associado associadoNaoCadastradoParaVotar;

        @Mock
        private AssociadoPauta associadoPauta;

        @Captor
        ArgumentCaptor<AssociadoPauta> captor;

        public static final String PATH = "/v1/voto";

        @BeforeEach
        void init() {
                MockitoAnnotations.openMocks(this);

                associado = new Associado();
                pauta = new Pauta();
                sessao = new Sessao();
                voto = new Voto();
                votoForaHorario = new Voto();
                associadoNaoCadastradoParaVotar = new Associado();
                associadoPauta = new AssociadoPauta();
                votoPauta = new VotoPauta();

                associado.setCPF("013.809.290-75");
                associadoNaoCadastradoParaVotar.setCPF("673.591.100-84");

                sessao.setInicioSessao(LocalDateTime.now());
                sessao.setFinalSessao(sessao.getInicioSessao().plusMinutes(5));

                pauta.setId("teste");
                pauta.setTitulo("Pauta Teste");
                pauta.setDescricao("Teste");
                pauta.setDataCriacao(LocalDateTime.now());
                pauta.setSessao(sessao);

                voto.setVoto(EnumVoto.SIM);
                voto.setDataHoraVoto(sessao.getInicioSessao().plusMinutes(2));

                votoForaHorario.setVoto(EnumVoto.SIM);
                votoForaHorario.setDataHoraVoto(sessao.getInicioSessao().plusMinutes(10));

                votoPauta.setId("teste");
                votoPauta.setPauta(pauta);
                votoPauta.setVoto(voto);

                associadoPauta.setId("teste");
                associadoPauta.setVotou(true);
                associadoPauta.setPauta(pauta);
                associadoPauta.setAssociado(associado);
        }

        @Test
        void deveCadastrarVoto() throws Exception {

                Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenReturn(pauta);

                Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any())).thenReturn(votoPauta);

                ObjectMapper mapper = new ObjectMapper();

                String json = mapper.writeValueAsString(new VotoPautaRequest(
                                associado.getCPF(),
                                pauta.getId(),
                                new VotoRequest(voto)));

                this.mockMvc.perform(post(PATH)
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isCreated());
        }

        @Test
        void deveRetornarNotFoundExceptionCasoPautaNaoExista() throws Exception {

                Mockito.when(pautaService.buscarPautaPorID(Mockito.any())).thenThrow(NotFoundException.class);

                ObjectMapper mapper = new ObjectMapper();

                String json = mapper.writeValueAsString(new VotoPautaRequest(
                                associado.getCPF(),
                                pauta.getId(),
                                new VotoRequest(voto)));

                this.mockMvc.perform(post(PATH)
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }

        @Test
        void deveRetornarNotFoundExceptionCasoAssociadoNaoEstejaCadastrado() throws Exception {

                Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any()))
                                .thenThrow(NotFoundException.class);

                ObjectMapper mapper = new ObjectMapper();

                String json = mapper.writeValueAsString(new VotoPautaRequest(
                                associado.getCPF(),
                                pauta.getId(),
                                new VotoRequest(voto)));

                this.mockMvc.perform(post(PATH)
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }

        @Test
        void deveRetornarConflictExceptionVotoEstejaForaDoHorario() throws Exception {

                Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any()))
                                .thenThrow(ConflictException.class);

                ObjectMapper mapper = new ObjectMapper();

                String json = mapper.writeValueAsString(new VotoPautaRequest(
                                associado.getCPF(),
                                pauta.getId(),
                                new VotoRequest(votoForaHorario)));

                this.mockMvc.perform(post(PATH)
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isConflict());
        }

        @Test
        void deveRetornarNotFoundExceptionSeAssociadoNaoCadastradoNaPauta() throws Exception {

                Mockito.when(votoService.cadastrarVoto(Mockito.any(), Mockito.any()))
                                .thenThrow(NotFoundException.class);

                ObjectMapper mapper = new ObjectMapper();

                String json = mapper.writeValueAsString(new VotoPautaRequest(
                                associadoNaoCadastradoParaVotar.getCPF(),
                                pauta.getId(),
                                new VotoRequest(votoForaHorario)));

                this.mockMvc.perform(post(PATH)
                                .content(json)
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON))
                                .andExpect(status().isNotFound());
        }

}