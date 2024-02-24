package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<Cidade> todasCidades() {
//		USA O GERENTE PARA CRIAR UM QUERY EM CIADDE E PEGAR TODOS OS RESULTADOS
		return manager.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscarPorId(Long id) {
		return manager.find(Cidade.class, id);
	}

	@Transactional
	@Override
	public Cidade AdicionarCidade(Cidade cidade) {
		return manager.merge(cidade);
	}

	@Transactional
	@Override
	public void removerCidade(Cidade cidade) {
		cidade = buscarPorId(cidade.getId());
		manager.remove(cidade);
	}

}
