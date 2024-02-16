package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class CozinhaRepositoryImpl implements CozinhaRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cozinha> listar(){
//		NÃO FUNCIONOU, AINDA NÃO SEI O PQ!
//		todo
		return em.createQuery("from Cozinha",Cozinha.class).getResultList();
	}
	
//	DEVOLVER UMA INSTANCIA DE COZINHA. QUE FOI BUSCADA POR ID COM O .find
	@Override
	public Cozinha buscarCozinha(Long id) {
		return em.find(Cozinha.class, id);
	}
	
//	SIMULA UMA TRANSAÇÃO
	@Override
	@Transactional
	public Cozinha salvarCozinha(Cozinha cozinha) {
		return em.merge(cozinha);
	}
	
	@Override
	@Transactional
	public void remover(Cozinha cozinha) {
//		E NECESSÁRIO BUSCAR POIS A INSTACIA COZINHA NAO ESTA SENDO GERENCIADA
//		QUANDO USA O METODO buscar A COZINHA PASSA A SER GERENCIADA E PODE SER REMOVIDA
		cozinha = buscarCozinha(cozinha.getId());
		em.remove(cozinha);
	}
	

	
	
}
