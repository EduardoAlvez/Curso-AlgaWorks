package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;

public interface RestauranteRepository {

	List<Restaurante> todasRestaurantes();
	Restaurante salvar(Restaurante restaurante);
	Restaurante buscarPorID(Long id);
	void remover(Long id);
	
}
