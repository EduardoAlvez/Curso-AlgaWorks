package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		
//		INICIA A APLICAÇÃO
		ApplicationContext app = new SpringApplicationBuilder(
				AlgafoodApiApplication.class)
//				USA NONE PRA DEFINIR QUE NÃO E UMA APLICAÇÃO WEB
				.web(WebApplicationType.NONE)
				.run(args);
		
		CozinhaRepository cozinhas = app.getBean(CozinhaRepository.class);
		
		List<Cozinha> todasCozinhas = cozinhas.todas();
		for (Cozinha cozinha : todasCozinhas) {
			System.out.println(cozinha.getNome());
		}
		
//		todasCozinhas.stream().forEach(c -> System.out.println(c.getNome()));
	
	}
}
