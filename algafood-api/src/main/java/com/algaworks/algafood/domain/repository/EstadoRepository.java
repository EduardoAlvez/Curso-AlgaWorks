package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> todosEstados();
	Estado buscarPorId(Long id);
	Estado adicionarEstado(Estado estado);
	void remover(Estado estado);

}