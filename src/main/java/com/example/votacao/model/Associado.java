package com.example.votacao.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "associado")
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Associado extends AbstrataEntitdade {

    @Column(name = "cpf", unique = true, nullable = false, length = 14)
    private String CPF;
}
