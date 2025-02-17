package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;

import jakarta.persistence.EntityManager;

// Usanmos generics"<T, ID>" para deixa tudo dinamico 
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> implements CustomJpaRepository<T, ID> {

	private EntityManager manager;
	
	
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.manager = entityManager;
	}

	
	@Override
	public Optional<T> buscarPrimeiro() {
		
		// "getDomainClass()" pega a classe de forma dinamica
		var jpql = "from " + getDomainClass().getName();
		
		T entidade = manager.createQuery(jpql,getDomainClass())
				.setMaxResults(1)	// Defini o tamanho maximo de resultados
				.getSingleResult(); // Pega apenas um resultado
				
		return Optional.ofNullable(entidade);
	}

	
	
}
