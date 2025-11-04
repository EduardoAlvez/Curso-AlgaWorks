package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EstadoService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Nao existe um cadastro com estado com o codigo %d .";
	private static final String MSG_ESTADO_EM_USO = "Estado de codigo %d nao pode ser removido, pois está em uso.";

	@Autowired
	private EstadoRepository estadoRepository;


	@Transactional
	public List<Estado> todos(){
		return estadoRepository.findAll();
	}

	@Transactional
	public Estado buscarOuFalhar(Long id) {

		return estadoRepository.findById(id).orElseThrow(()-> new EstadoNaoEncontradaException(
				String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}

	@Transactional
	public Estado salvar(Estado estado) {

		return estadoRepository.save(estado);
	}

	@Transactional
	public void remover(Long id) {
		if(!estadoRepository.existsById(id))
			throw new EstadoNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id));

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
