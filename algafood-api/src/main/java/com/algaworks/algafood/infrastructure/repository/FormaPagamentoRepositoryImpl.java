package com.algaworks.algafood.infrastructure.repository;

import java.util.List;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager gerente;
	
	@Override
	public List<FormaPagamento> todasFormaPagamentos() {
		return gerente.createQuery("from FormaPagamento", FormaPagamento.class).getResultList();
	}

	@Override
	public FormaPagamento buscarPorId(Long id) {
		return gerente.find(FormaPagamento.class, id);
	}

	@Transactional
	@Override
	public FormaPagamento adicionarFormaPagamento(FormaPagamento formaPagamento) {
		return gerente.merge(formaPagamento);
	}

	@Transactional
	@Override
	public void remover(FormaPagamento formaPagamento) {
		formaPagamento = buscarPorId(formaPagamento.getId());
		gerente.remove(formaPagamento);
	}

}
