package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
//	@JoinColumn(name = "cozinha_codigo") PARA MODEIFICA UMA KEY DEVE APLICAR O JOIN
	@JoinColumn(name = "cozinha_id", nullable = false)
	private Cozinha cozinha;

	
//	METODOS
	public void setTaxafrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

}
