package com.example.votacao.repository;
import com.example.votacao.model.Associado;
import com.example.votacao.model.AssociadoPauta;
import com.example.votacao.model.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AssociadoPautaRepository extends JpaRepository<AssociadoPauta, String> {
    public Optional<AssociadoPauta> findByAssociadoAndPauta(Associado associado, Pauta pauta);
}
