package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager gerente;
	
	@Override
	public List<Estado> todosEstados() {
		return gerente.createQuery("from Estado",Estado.class).getResultList();
	}

	@Override
	public Estado buscarPorId(Long id) {
		return gerente.find(Estado.class, id);
	}

	@Transactional
	@Override
	public Estado adicionarEstado(Estado estado) {
		return gerente.merge(estado);
	}

	@Transactional
	@Override
	public void remover(Estado estado) {
		estado = buscarPorId(estado.getId());
	}

}
