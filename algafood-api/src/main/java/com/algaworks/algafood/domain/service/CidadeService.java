package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	
	public List<Cidade> todos() {
		return cidadeRepository.todas();
	}
	
	public Cidade buscarPorId(Long id) {
		Cidade cidade = cidadeRepository.buscarPorId(id);
		
		if(cidade != null)
			return cidade;
		
		throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrado.", id));
	}
	
	public Cidade salvar(Cidade cidade) {
		// USADO PRA ADICIONAR E PARA ATUALIZAR, POIS USA O MERGE.
		return cidadeRepository.salvar(cidade);
	}
	
	public void remover(long id) {
		try {
			cidadeRepository.remover(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(String.format("Cidade de id %d não encontrado.", id));
		}
	}
}
