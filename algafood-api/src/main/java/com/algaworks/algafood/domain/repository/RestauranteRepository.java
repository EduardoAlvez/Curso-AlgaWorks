package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todasRestaurantes();
	Restaurante porIDRestaurante(Long id);
	Restaurante adicionarRestaurante(Restaurante restaurante);
	void remover(Restaurante restaurante);
	
}
