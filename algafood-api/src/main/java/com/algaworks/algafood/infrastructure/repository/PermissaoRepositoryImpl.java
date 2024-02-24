package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager gerente;
	
	@Override
	public List<Permissao> todasPermissoes() {
		return gerente.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscarPorId(Long id) {
		return gerente.find(Permissao.class, id);
	}

	@Transactional
	@Override
	public Permissao adicionarPermissao(Permissao permissao) {
		return gerente.merge(permissao);
	}

	@Transactional
	@Override
	public void remover(Permissao permissao) {
		permissao = buscarPorId(permissao.getId());
		gerente.remove(permissao);
		
	}

}
