package com.algaworks.algafood.api.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;


@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/restaurante/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNome(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFretefinal) {
		
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFretefinal);
	}
	
}
