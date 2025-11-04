package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CidadeService {

	private static final String MSG_CIDADE_EM_USO = "Cidade de codigo %d nao pode ser removido, pois está em uso.";

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private EstadoService estadoService;

	@Transactional
	public List<Cidade> todos() {
		return cidadeRepository.findAll();
	}

	@Transactional
	public Optional<Cidade> buscarOuFalhar(Long id) {
		return Optional.ofNullable(cidadeRepository.findById(id).orElseThrow(() -> new CidadeNaoEncontradaException(id)));
	}
	
	// SALVAR E ATUALIZAR
	@Transactional
	public Cidade salvar(Cidade cidade) {
		// USADO PRA ADICIONAR E PARA ATUALIZAR, POIS USA O MERGE.
		Long estadoId = cidade.getEstado().getId();

		Estado estado = estadoService.buscarOuFalhar(estadoId);

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	@Transactional
	public void remover(long id) {
		try {
			cidadeRepository.deleteById(id);
		} catch (EmptyResultDataAccessException  | IllegalArgumentException e) {
			throw new CidadeNaoEncontradaException(id);
		} catch (DataIntegrityViolationException e){
			throw new EntidadeEmUsoException((String.format(MSG_CIDADE_EM_USO, id)));
		}
	}
}
