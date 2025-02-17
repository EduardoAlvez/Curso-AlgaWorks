package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;



@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNome(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}
	
	
	// Padrão de projeto "especification"
	@GetMapping("/restaurantes/por-nome-e-frete-gratis")
	public List<Restaurante> restaurantesPorNome(String nome) {
//		AULA 29
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);
//		return restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));
		
//		Usando chamada staticas com o padrão Factory
//		return restauranteRepository.findAll(ComFreteGratis().and(comNomeSemelhante(nome)));
		
//		Usando a nossa interface, deixando assim tudo padronizado e sem excessos
		return restauranteRepository.findComFreteGratis(nome);
	}
	
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro() {
		return restauranteRepository.buscarPrimeiro();
	}
	
	@GetMapping("/cozinhas/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro() {
		return cozinhaRepository.buscarPrimeiro();
	}
	
	
}
