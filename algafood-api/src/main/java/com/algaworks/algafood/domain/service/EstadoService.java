package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Nao existe um cadastro com estado com o codigo %d .";
	private static final String MSG_ESTADO_EM_USO = "Estado de codigo %d nao pode ser removido, pois está em uso.";

	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public List<Estado> todos(){
		return estadoRepository.findAll();
	}
	
	public Estado buscarOuFalhar(Long id) {

		return estadoRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(
				String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}
	
	public Estado salvar(Estado estado) {

		return estadoRepository.save(estado);
	}
	
	public void remover(Long id) {
		if(!estadoRepository.existsById(id))
			throw new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));

		try {
			estadoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
                    MSG_ESTADO_EM_USO.formatted(id));
		}
	}
	
	
	/*
	 * catch (EmptyResultDataAccessException e) { throw new
	 * EntidadeNaoEncontradaException(
	 * "Não existe um estado com o ID: %d".formatted(id)); }
	 */
}
