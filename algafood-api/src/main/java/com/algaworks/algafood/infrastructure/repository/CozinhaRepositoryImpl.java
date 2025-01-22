/*
 * package com.algaworks.algafood.infrastructure.repository;
 * 
 * import java.util.List;
 * 
 * import org.springframework.dao.EmptyResultDataAccessException; import
 * org.springframework.stereotype.Component;
 * 
 * import com.algaworks.algafood.domain.model.Cozinha; import
 * com.algaworks.algafood.domain.repository.CozinhaRepository;
 * 
 * import jakarta.persistence.EntityManager; import
 * jakarta.persistence.PersistenceContext; import
 * jakarta.transaction.Transactional;
 * 
 * // TINHA ESQUECIDO DE COLOCAR @Component
 * 
 * @Component public class CozinhaRepositoryImpl implements CozinhaRepository{
 * 
 * @PersistenceContext private EntityManager em;
 * 
 * @Override public List<Cozinha> todas(){ // NÃO FUNCIONOU, AINDA NÃO SEI O PQ!
 * // todo return em.createQuery("from Cozinha",Cozinha.class).getResultList();
 * }
 * 
 * // DEVOLVER UMA INSTANCIA DE COZINHA. QUE FOI BUSCADA POR ID COM O .find
 * 
 * @Override public Cozinha buscarPorId(Long id) { return em.find(Cozinha.class,
 * id); }
 * 
 * // SIMULA UMA TRANSAÇÃO
 * 
 * @Transactional
 * 
 * @Override public Cozinha salvar(Cozinha cozinha) { return em.merge(cozinha);
 * }
 * 
 * @Transactional
 * 
 * @Override public void remover(Long id) { // E NECESSÁRIO BUSCAR POIS A
 * INSTACIA COZINHA NAO ESTA SENDO GERENCIADA // QUANDO USA O METODO buscar A
 * COZINHA PASSA A SER GERENCIADA E PODE SER REMOVIDA Cozinha cozinha =
 * buscarPorId(id); if(cozinha == null) throw new
 * EmptyResultDataAccessException(1);
 * 
 * em.remove(cozinha); } }
 */