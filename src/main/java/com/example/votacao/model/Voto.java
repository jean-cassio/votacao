package com.example.votacao.model;

import com.example.votacao.enums.EnumVoto;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDateTime;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Voto {

    @Enumerated(EnumType.STRING)
    private EnumVoto voto;

    private LocalDateTime dataHoraVoto;
}
