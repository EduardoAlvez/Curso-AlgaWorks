package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

// INTERFACE DE LIGAÇÃO
public interface RestauranteRepositoryQueries {

	public List<Restaurante> find(String nome , BigDecimal valorInicial, BigDecimal valorFinal);
	
	public List<Restaurante> findComFreteGratis(String nome);
}
