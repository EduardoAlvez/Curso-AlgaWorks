package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Permissao;

public interface PermissaoRepository {

	List<Permissao> todasPermissoes();
	Permissao buscarPorId(Long id);
	Permissao adicionarPermissao(Permissao permissao);
	void remover(Permissao permissao);
}
