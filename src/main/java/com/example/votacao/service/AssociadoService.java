package com.example.votacao.service;

import com.example.votacao.exceptions.ConflictException;
import com.example.votacao.exceptions.NotFoundException;
import com.example.votacao.model.Associado;
import com.example.votacao.repository.AssociadoRepository;
import com.example.votacao.service.interfaces.IAssociadoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService implements IAssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }

    @Override
    @Transactional
    public Associado criarAssociado(Associado associado) {

        if (associadoEstaCadastrado(associado))
            throw new ConflictException("Associado já cadastrado!");

        return associadoRepository.save(associado);
    }

    @Override
    public Associado buscarPorCPF(String cpf){

        Optional<Associado> associado = associadoRepository.findByCPF(cpf);

        return associado.orElseThrow(() -> new NotFoundException("Associado não encontrado!"));
    }

    public Boolean associadoEstaCadastrado(Associado associado){
        return associadoRepository.findByCPF(associado.getCPF()).isPresent();
    }
}
