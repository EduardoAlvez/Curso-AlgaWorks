package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> todos();
	Estado buscarPorId(Long id);
	Estado salvar(Estado estado);
	void remover(Estado estado);

}
