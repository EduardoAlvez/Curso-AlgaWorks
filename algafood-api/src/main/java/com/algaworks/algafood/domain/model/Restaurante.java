package com.algaworks.algafood.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@OneToMany(mappedBy = "restaurante")
	private List<Produto> produtos = new ArrayList<Produto>();
	

	@ManyToMany
	@JoinTable(name = "restaurante_forma_pagamento", 
		joinColumns = @JoinColumn(referencedColumnName = "restaurante_id"), 
		inverseJoinColumns = @JoinColumn(referencedColumnName = "forma_pagamento_id"))
	private List<FormaPagamento> formaPagamentos = new ArrayList<>();





//	METODOS
	public void setTaxafrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

}
