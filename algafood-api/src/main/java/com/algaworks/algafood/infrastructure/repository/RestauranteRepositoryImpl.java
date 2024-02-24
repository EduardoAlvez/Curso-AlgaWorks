package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{

	@PersistenceContext
	private EntityManager em;

	
//	MÉTODOS
	@Override
	public List<Restaurante> todasRestaurantes(){				 
		return em.createQuery("from Restaurante",Restaurante.class).getResultList();
	}
	
//	DEVOLVER UMA INSTANCIA DE COZINHA. QUE FOI BUSCADA POR ID COM O .find
	@Override
	public Restaurante porIDRestaurante(Long id) {
		return em.find(Restaurante.class, id);
	}
	
//	SIMULA UMA TRANSAÇÃO
	@Transactional
	@Override
	public Restaurante adicionarRestaurante(Restaurante restaurante) {
		return em.merge(restaurante);
	}
	
	@Transactional
	@Override
	public void remover(Restaurante restaurante) {
//		E NECESSÁRIO BUSCAR POIS A INSTACIA COZINHA NAO ESTA SENDO GERENCIADA
//		QUANDO USA O METODO buscar A COZINHA PASSA A SER GERENCIADA E PODE SER REMOVIDA
		restaurante = porIDRestaurante(restaurante.getId());
		em.remove(restaurante);
	}
	

	
	
}
