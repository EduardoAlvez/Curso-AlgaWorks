
package com.algaworks.algafood.infrastructure.repository;

import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.ComFreteGratis;
import static com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired 
	@Lazy // ajuda a quebra referências circulares; 
	private RestauranteRepository restauranteRepository;

	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {

//		System.out.println("Taxa Frete Final recebida: " + taxaFreteFinal);
		
		// PARA CONSULTAS MUITO SIMPLES EVITAR USAR, JPQL E MAIS FÁCIL
		// VAMOS APRENDER A USAR O "CRITERIAQUERY"
		
		// Cria instancia da "Criteriaquery", e metodos do jpql
		CriteriaBuilder builder = manager.getCriteriaBuilder(); 
		
		// Tem varios métodos como "select"...
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		
		// Usando os métodos
		Root<Restaurante> root = criteria.from(Restaurante.class); // equivale no Jpql, "from Restaurante";
		
		
		// Aula 29
		
		var predicates = new ArrayList<>();
		
		if (StringUtils.hasText(nome)) {
			predicates.add(builder
					.like(root.get("nome"), "%"+ nome + "%")); 			
		}
		
		if(taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));			
		}
		
		if(taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));			
		}
		
		
		criteria.where(predicates.toArray(new Predicate[0]));
		
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		
		return query.getResultList();
				
	}

	@Override
	public List<Restaurante> findComFreteGratis(String nome) {
		return restauranteRepository.findAll(ComFreteGratis().and(comNomeSemelhante(nome)));
	}

	
	
	
	
	
//		ESSA FORAM DUAS FORMA DE USAR O "JPQL" PARA CONSULTAS
//		FORMA MANUAL
//		var jpql = "from Restaurante"
//				+ "where like :nome"
//				+ "and taxafrete between :valorInicial and :valorFinal";
//		
//		return gerente.createQuery(jpql, Restaurante.class)
//				.setParameter("nome","%"+ nome +"%")
//				.setParameter("valorInicial", valorInicial)
//				.setParameter("calorFinal", valorFinal)
//				.getResultList();
	
//		FORMA DINAMICA
//		var paramentros = new HashMap<String,Object>();
//		
//		var jpql = new StringBuilder();
//		jpql.append("from Restaurante where 0 = 0 ");
//		
//		if(StringUtils.hasLength(nome)) {
//			jpql.append("and nome like :nome ");
//			paramentros.put("nome", "%"+nome+"%");
//		}
//		
//		if(valorInicial != null) {
//			jpql.append("and taxafrete >= :valorInicial");
//			paramentros.put("valorInicial", valorInicial);
//			
//		}
//		
//		if (valorFinal != null) {
//			jpql.append("and taxafrete <= :valorFinal");
//			paramentros.put("valorFinal", valorFinal);
//		}
//		
//		
//		return gerente.createQuery(jpql.toString(), Restaurante.class).getResultList();

	/*
	 * // MÉTODOS
	 * 
	 * @Override public List<Restaurante> todasRestaurantes() { return
	 * gerente.createQuery("from Restaurante", Restaurante.class).getResultList(); }
	 * 
	 * // DEVOLVER UMA INSTANCIA DE COZINHA. QUE FOI BUSCADA POR ID COM O .find
	 * 
	 * @Override public Restaurante buscarPorID(Long id) { return
	 * gerente.find(Restaurante.class, id); }
	 * 
	 * @Transactional
	 * 
	 * @Override public void remover(Long id) { // E NECESSÁRIO BUSCAR POIS A
	 * INSTACIA COZINHA NAO ESTA SENDO GERENCIADA // QUANDO USA O METODO buscar A
	 * COZINHA PASSA A SER GERENCIADA E PODE SER REMOVIDA Restaurante restaurante =
	 * buscarPorID(id); if(restaurante == null) throw new
	 * EmptyResultDataAccessException(1);
	 * 
	 * gerente.remove(restaurante); }
	 * 
	 * @Transactional
	 * 
	 * @Override public Restaurante salvar(Restaurante restaurante) { return
	 * gerente.merge(restaurante); }
	 */
}
