package com.algaworks.algafood.domain.repository;

import java.util.List;

import com.algaworks.algafood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todasFormaPagamentos();
	FormaPagamento buscarPorId(Long id);
	FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento);
	void remover(FormaPagamento formaPagamento);
}
