package com.algaworks.algafood.domain.model;

import com.algaworks.algafood.core.validation.Grupos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Cidade {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "nome", nullable = false)
	private String nome;

	@Valid
	@ConvertGroup(from = Default.class, to = Grupos.EstadoId.class)
	@NotNull(groups = Grupos.EstadoId.class)
	@ManyToOne
	@JoinColumn(name = "estado_id",nullable = false)
	private Estado estado;
}
