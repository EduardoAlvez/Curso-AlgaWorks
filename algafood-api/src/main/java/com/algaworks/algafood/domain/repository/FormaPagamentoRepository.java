package com.algaworks.algafood.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{

//	List<FormaPagamento> todasFormaPagamentos();
//	FormaPagamento buscarPorId(Long id);
//	FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento);
//	void remover(FormaPagamento formaPagamento);
	
}
