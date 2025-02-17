package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	// O NOME DO MÉTODO VIRA UMA EXPRESSAO.
	// JPA POSSUI KEY WORDS, TODAS ESTAO NA DOCUMENTAÇÃO DO JPA
	
	@Query("from Cozinha where nome like %:nome%") // DESSA FORMA PODEMOS FAZER CONSULTAR POR JPQL, PODE VIRAR UM PROBLEMA A MEDIDA QUE VAI CRESCENDO
	List<Cozinha> consultaPorNome(String nome); // AINDA TEREMOS UM NOME DE METODO EXPLICATIVO E SIMPLES
	
	List<Cozinha> findTodasByNomeContaining(String nome); // Containing = like %:nome%
	List<Cozinha> findTodasByNome(String nome); // JPA CONSEGUE CRIAR METODOS USANDO OS PARAMENTROS IDENTICOS AO MODELO.
	Optional<Cozinha> findByNome(String nome); // "FIND(TEXTO)BY" É OPICIONAL E PODE TER TEXTO ENTRE OS DOIS.
//	List<Cozinha> todas();
//	Cozinha buscarPorId(Long id);
//	Cozinha salvar(Cozinha cozinha);
//	void remover(Long id);
	
}
