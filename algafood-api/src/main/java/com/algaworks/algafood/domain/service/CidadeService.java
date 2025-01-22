package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	
	public List<Cidade> todos() {
		return cidadeRepository.findAll();
	}
	
	public Optional<Cidade> buscarPorId(Long id) {
//		Optional<Cidade> cidade = cidadeRepository.findById(id);
		return cidadeRepository.findById(id);
		
//		if(cidade != null)
//			return cidade;
//		
//		throw new EntidadeNaoEncontradaException("Cidade de id %d não encontrado.".formatted(id));
	}
	
	// SALVAR E ATUALIZAR
	public Cidade salvar(Cidade cidade) {
		// USADO PRA ADICIONAR E PARA ATUALIZAR, POIS USA O MERGE.
		Estado estado = estadoRepository.findById(cidade.getEstado().getId())
				.orElseThrow(()-> new EntidadeNaoEncontradaException(String.format("Não existe cadastro de estado com código %d", cidade.getEstado().getId())));
		
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
//		if (estado == null)	
//			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de estado com código %d", cidade.getEstado().getId()));
//		
//		cidade.setEstado(estado);
		
	}
	
	public void remover(long id) {
		if(!cidadeRepository.existsById(id))
			throw new EntidadeNaoEncontradaException("Cidade de id %d não encontrado.".formatted(id));
		
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException  | IllegalArgumentException e) {
			throw new EntidadeNaoEncontradaException("Cidade de id %d não encontrado.".formatted(id));
		}
	}
}
