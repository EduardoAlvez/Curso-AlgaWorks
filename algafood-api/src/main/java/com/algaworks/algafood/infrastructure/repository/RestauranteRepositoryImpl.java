
package com.algaworks.algafood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager gerente;

	@Override
	public List<Restaurante> find(String nome, BigDecimal valorInicial, BigDecimal valorFinal) {
		
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
		var paramentros = new HashMap<String,Object>();
		
		var jpql = new StringBuilder();
		jpql.append("from Restaurante where 0 = 0 ");
		
		if(StringUtils.hasLength(nome)) {
			jpql.append("and nome like :nome ");
			paramentros.put("nome", "%"+nome+"%");
		}
		
		if(valorInicial != null) {
			jpql.append("and taxafrete >= :valorInicial");
			paramentros.put("valorInicial", valorInicial);
			
		}
		
		if (valorFinal != null) {
			jpql.append("and taxafrete <= :valorFinal");
			paramentros.put("valorFinal", valorFinal);
		}
		
		
		return gerente.createQuery(jpql.toString(), Restaurante.class).getResultList();
		
		
	}

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
