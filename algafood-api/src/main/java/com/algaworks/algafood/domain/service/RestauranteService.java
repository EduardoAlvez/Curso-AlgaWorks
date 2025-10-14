package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradaException;
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

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Nao existe um cadastro de estado com o codigo %d .";

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;
	
	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}
	
	public Restaurante salvar(Restaurante restaurante) {
		
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(idCozinha);
		
		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}
	
	public void remover(Long id) {
		try {
			restauranteRepository.deleteById(id);
			
		} catch (IllegalArgumentException e) {
			throw new CozinhaNaoEncontradaException(
                    "Não existe uma cozinha com o ID: %d.".formatted(id));

		} catch (EmptyResultDataAccessException e){
			throw new RestauranteNaoEncontradaException(id);
		}
	}
	
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}
	
	
	/*
	 * NAO SE USA MAIS.
	 * catch (EmptyResultDataAccessException e) { throw new
	 * EntidadeNaoEncontradaException(
	 * "Não existe uma Restaurante com o ID: %d".formatted(id)); }
	 */
}
