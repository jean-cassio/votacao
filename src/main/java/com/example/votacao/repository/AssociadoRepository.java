package com.example.votacao.repository;
import com.example.votacao.model.Associado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, String> {

    public Optional<Associado> findByCPF(String CPF);
}
