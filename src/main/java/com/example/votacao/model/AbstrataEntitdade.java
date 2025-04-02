package com.example.votacao.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@MappedSuperclass
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstrataEntitdade {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(length = 36, nullable = false)
	protected String id;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "data_criacao", nullable = false, updatable = false)
	protected LocalDateTime dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy'T'HH:mm:ss'Z'", timezone = "GMT")
	@Column(name = "data_atualizacao")
	protected LocalDateTime dataAtualizacao;

	// Getters e setters

	@PrePersist
	protected void onCreate() {
		dataCriacao = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		dataAtualizacao = LocalDateTime.now();
	}
}
