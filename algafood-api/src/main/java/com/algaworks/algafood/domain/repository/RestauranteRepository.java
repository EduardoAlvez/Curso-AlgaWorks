package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository 
	extends JpaRepository<Restaurante, Long>, RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante> {
// RestauranteRepositoryQueries tem metodos personalizados
	
//	List<Restaurante> todasRestaurantes();
//	Restaurante salvar(Restaurante restaurante);
//	Restaurante buscarPorID(Long id);
//	void remover(Long id);
	
}
