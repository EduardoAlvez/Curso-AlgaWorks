package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(idCozinha)
				.orElseThrow(()-> new EntidadeNaoEncontradaException(
						String.format("Não existe cadastro de cozinha com código %d", restaurante.getCozinha().getId())));
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
//		if(cozinha == null)
//			throw new EntidadeNaoEncontradaException(
//					String.format("Não existe cadastro de cozinha com código %d", restaurante.getCozinha().getId()));		
	}
	
	public void remover(Long id) {
		if(!restauranteRepository.existsById(id)) 
			throw new EntidadeNaoEncontradaException(
					"Não existe uma Restaurante com o ID: %d".formatted(id));			
			
		try {
			restauranteRepository.deleteById(id);
			
		}catch (IllegalArgumentException e) {
			throw new EntidadeNaoEncontradaException(
                    "Não existe uma cozinha com o ID: %d.".formatted(id));
		}
	}
	
	public Optional<Restaurante> buscarPorID(Long id) {
		return restauranteRepository.findById(id);
	}
	
	
	/*
	 * NAO SE USA MAIS.
	 * catch (EmptyResultDataAccessException e) { throw new
	 * EntidadeNaoEncontradaException(
	 * "Não existe uma Restaurante com o ID: %d".formatted(id)); }
	 */
}
