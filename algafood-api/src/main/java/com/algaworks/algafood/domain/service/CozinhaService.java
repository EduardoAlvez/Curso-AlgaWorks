package com.algaworks.algafood.domain.service;

import java.text.Format;
import java.util.List;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de codigo %d nao pode ser removido, pois está em uso.";

	@Autowired
	private CozinhaRepository cozinhaRepository;


	@Transactional
	public List<Cozinha> todas() {
		return cozinhaRepository.findAll();
	}

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public Cozinha buscarOuFalhar(Long id){
		return cozinhaRepository.findById(id).orElseThrow(()-> new CozinhaNaoEncontradaException(id));
	}

	@Transactional
	public void remover(Long id) {
		if (!cozinhaRepository.existsById(id))
			throw new CozinhaNaoEncontradaException(id);

		try {
			cozinhaRepository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
                   MSG_COZINHA_EM_USO.formatted(id));
		}catch (EmptyResultDataAccessException e){
			throw new CozinhaNaoEncontradaException(id);
		}
		
		
//		catch (EmptyResultDataAccessException e) { NÃO E LANÇADA MAIS
//			throw new EntidadeNaoEncontradaException(
//                    "Não existe uma cozinha com o ID: %d.".formatted(id));
//		}
	}
}
