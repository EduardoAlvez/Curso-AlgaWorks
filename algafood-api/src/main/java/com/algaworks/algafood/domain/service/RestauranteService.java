package com.algaworks.algafood.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import com.algaworks.algafood.api.model.dtos.saida.CozinhaModel;
import com.algaworks.algafood.api.model.dtos.saida.RestauranteModel;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Nao existe um cadastro de estado com o codigo %d .";

	@Autowired
	private RestauranteRepository restauranteRepository;
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cozinhaService;

	@Transactional
	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}

	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Long idCozinha = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaService.buscarOuFalhar(idCozinha);

		restaurante.setCozinha(cozinha);
		return restauranteRepository.save(restaurante);
	}

	@Transactional
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

	@Transactional
	public Restaurante buscarOuFalhar(Long id) {
		return restauranteRepository.findById(id).orElseThrow(()-> new RestauranteNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, id)));
	}



	/*
	 * NAO SE USA MAIS.
	 * catch (EmptyResultDataAccessException e) { throw new
	 * EntidadeNaoEncontradaException(
	 * "Não existe uma Restaurante com o ID: %d".formatted(id)); }
	 */
	private RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = new RestauranteModel();
		CozinhaModel cozinhaModel = new CozinhaModel();

		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setNome(restaurante.getCozinha().getNome());

		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setId(restaurante.getId());
		restauranteModel.setCozinha(cozinhaModel);

		return restauranteModel;
	}

	private List<RestauranteModel> toCollertionModel(List<Restaurante> restaurantes){
		return restaurantes.stream().map(this::toModel).collect(Collectors.toList());
	}
}
