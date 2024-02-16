package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;

public interface CozinhaRepository {

	List<Cozinha> todasCozinhas();
	Cozinha porIDCozinha(Long id);
	Cozinha adicionarCozinha(Cozinha cozinha);
	void remover(Cozinha cozinha);
	
}
