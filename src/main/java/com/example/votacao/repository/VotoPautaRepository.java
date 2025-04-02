package com.example.votacao.repository;
import com.example.votacao.model.Pauta;
import com.example.votacao.model.VotoPauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VotoPautaRepository extends JpaRepository<VotoPauta, String> {
    List<VotoPauta> findByPauta(Pauta pauta);
}
