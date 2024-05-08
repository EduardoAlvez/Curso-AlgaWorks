package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha = cozinhaRepository.buscarPorId(restaurante.getCozinha().getId());
		
		if(cozinha == null)
			throw new EntidadeNaoEncontradaException(String.format("Não existe cadastro de cozinha com código %d", restaurante.getCozinha().getId()));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.salvar(restaurante);
	}
	
	public void remover(Long id) {
		try {
			restauranteRepository.remover(id);
		}catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe uma Restaurante com o ID: %d", id));
		}
	}
	
	public Restaurante buscar(Long id) {
		return restauranteRepository.buscarPorID(id);
	}
}
