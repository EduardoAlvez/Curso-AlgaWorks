package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public List<Estado> todos(){
		return estadoRepository.findAll();
	}
	
	public Optional<Estado> buscarPorId(Long id) {
		return estadoRepository.findById(id);	
	}
	
	public Estado salvar(Estado estado) {

		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
		if(!estadoRepository.existsById(id))
			throw new EntidadeNaoEncontradaException(
                    "Não existe um estado com o ID: %d".formatted(id));
			
		try {
			estadoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
                    "Estado de código %d não pode ser removida, pois está em uso".formatted(id));
		}
	}
	
	
	/*
	 * catch (EmptyResultDataAccessException e) { throw new
	 * EntidadeNaoEncontradaException(
	 * "Não existe um estado com o ID: %d".formatted(id)); }
	 */
}
