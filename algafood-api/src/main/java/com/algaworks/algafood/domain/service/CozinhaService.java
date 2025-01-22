package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CozinhaService {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public List<Cozinha> todas() {
		return cozinhaRepository.findAll();
	}
	
	
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void remover(Long id) {
		if (!cozinhaRepository.existsById(id)) {
			throw new EntidadeNaoEncontradaException(
                    "Não existe uma cozinha com o ID: %d.".formatted(id));
		}
		
		try {
			cozinhaRepository.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
                    "Cozinha de código %d não pode ser removida, pois está em uso.".formatted(id));
		}
		
		
//		catch (EmptyResultDataAccessException e) { NÃO E LANÇADA MAIS
//			throw new EntidadeNaoEncontradaException(
//                    "Não existe uma cozinha com o ID: %d.".formatted(id));
//		}
	}
}
