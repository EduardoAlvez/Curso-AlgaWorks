package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cidade;

public interface CidadeRepository {
	
	List<Cidade> todasCidades();
	Cidade buscarPorId(Long id);
	Cidade AdicionarCidade(Cidade cidade);
	void removerCidade(Cidade cidade);

}
