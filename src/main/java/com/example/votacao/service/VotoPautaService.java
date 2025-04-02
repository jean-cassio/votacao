package com.example.votacao.service;

import com.example.votacao.enums.EnumVoto;
import com.example.votacao.model.Pauta;
import com.example.votacao.model.Voto;
import com.example.votacao.model.VotoPauta;
import com.example.votacao.repository.VotoPautaRepository;
import com.example.votacao.service.interfaces.IVotoPautaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotoPautaService implements IVotoPautaService {

    private final VotoPautaRepository votoPautaRepository;

    public VotoPautaService(VotoPautaRepository votoPautaRepository){
        this.votoPautaRepository = votoPautaRepository;
    }

    public VotoPauta cadastrarVotoPauta(VotoPauta votoPauta){
        return votoPautaRepository.save(votoPauta);
    }

    public Integer contagemVotos(Pauta pauta){
        List<VotoPauta> votosPauta = votoPautaRepository.findByPauta(pauta);
        return votosPauta.size();
    }


    public Integer contagemVotosPositivos(Pauta pauta){

        List<VotoPauta> votosPauta = votoPautaRepository.findByPauta(pauta);

        List<Voto> votos = votosPauta.stream().map(VotoPauta::getVoto).toList();

        return votos.stream().filter(v -> v.getVoto() == EnumVoto.SIM).toList().size();
    }

}
