package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class ConsultaCozinhaMain {

	public static void main(String[] args) {
		
//		INICIA A APLICAÇÃO
		ApplicationContext app = new SpringApplicationBuilder(
				AlgafoodApiApplication.class)
//				USA NONE PRA DEFINIR QUE NÃO E UMA APLICAÇÃO WEB
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = app.getBean(CadastroCozinha.class);
		
		List<Cozinha> cozinhas = cadastroCozinha.listar();
		for (Cozinha cozinha2 : cozinhas) {
			System.out.println(cozinha2.getNome());
		}
		
		cozinhas.stream().forEach(c -> System.out.println(c.getNome()));
	
	}
}
