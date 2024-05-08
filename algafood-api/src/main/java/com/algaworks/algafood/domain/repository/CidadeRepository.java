package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> todas();
	Cidade buscarPorId(Long id);
	Cidade salvar(Cidade cidade);
	void remover(Long id);

}
