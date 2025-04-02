package com.example.votacao.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "voto_pauta")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class VotoPauta extends AbstrataEntitdade{

    @Embedded
    private Voto voto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pauta pauta;
}
