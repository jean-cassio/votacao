package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.*;

@Entity
@Table(name = "pauta")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Pauta extends AbstrataEntitdade {

    private String titulo;
    private String descricao;

    @Embedded
    private Sessao sessao;

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Builder.Default
    private Set<AssociadoPauta> associados = new HashSet<>();

    @OneToMany(mappedBy = "pauta", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @Builder.Default
    private List<VotoPauta> votos = new ArrayList<>();

    @Builder.Default
    private Boolean ativo = true;
}
