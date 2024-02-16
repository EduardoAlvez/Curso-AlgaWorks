package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> listar();
	Cozinha buscarCozinha(Long id);
	Cozinha salvarCozinha(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
