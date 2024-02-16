package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Cozinha;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CadastroCozinha {

	@PersistenceContext
	private EntityManager em;
	
	public List<Cozinha> listar(){
//		NÃO FUNCIONOU, AINDA NÃO SEI O PQ!
//		todo
		return em.createQuery("from Cozinha",Cozinha.class).getResultList();
	}
	
//	DEVOLVER UMA INSTANCIA DE COZINHA. QUE FOI BUSCADA POR ID COM O .find
	public Cozinha buscar(Long id) {
		return em.find(Cozinha.class, id);
	}
	
//	SIMULA UMA TRANSAÇÃO
	@Transactional
	public Cozinha salvarCozinha(Cozinha cozinha) {
		return em.merge(cozinha);
	}
	
	@Transactional
	public void remover(Cozinha cozinha) {
//		E NECESSÁRIO BUSCAR POIS A INSTACIA COZINHA NAO ESTA SENDO GERENCIADA
//		QUANDO USA O METODO buscar A COZINHA PASSA A SER GERENCIADA E PODE SER REMOVIDA
		cozinha = buscar(cozinha.getId());
		em.remove(cozinha);
	}
	
}
