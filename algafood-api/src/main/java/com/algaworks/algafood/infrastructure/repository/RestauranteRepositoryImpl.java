/*
 * package com.algaworks.algafood.infrastructure.repository;
 * 
 * import java.util.List;
 * 
 * import org.springframework.dao.EmptyResultDataAccessException; import
 * org.springframework.stereotype.Component;
 * 
 * import com.algaworks.algafood.domain.model.Restaurante; import
 * com.algaworks.algafood.domain.repository.RestauranteRepository;
 * 
 * import jakarta.persistence.EntityManager; import
 * jakarta.persistence.PersistenceContext; import
 * jakarta.transaction.Transactional;
 * 
 * @Component public class RestauranteRepositoryImpl implements
 * RestauranteRepository{
 * 
 * @PersistenceContext private EntityManager gerente;
 * 
 * 
 * // MÉTODOS
 * 
 * @Override public List<Restaurante> todasRestaurantes(){ return
 * gerente.createQuery("from Restaurante",Restaurante.class).getResultList(); }
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
 * 
 * 
 * }
 */