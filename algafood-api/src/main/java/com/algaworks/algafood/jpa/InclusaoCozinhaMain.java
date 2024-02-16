package com.algaworks.algafood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Cozinha;

public class InclusaoCozinhaMain {

	public static void main(String[] args) {
		
//		INICIA A APLICAÇÃO
		ApplicationContext app = new SpringApplicationBuilder(
				AlgafoodApiApplication.class)
//				USA NONE PRA DEFINIR QUE NÃO E UMA APLICAÇÃO WEB
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = app.getBean(CadastroCozinha.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Brasileira");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Chinesa");
		
		cadastroCozinha.salvarCozinha(cozinha1);
		cadastroCozinha.salvarCozinha(cozinha2);

	}
}
