package com.algaworks.algafood.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.algaworks.algafood.AlgafoodApiApplication;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;

public class ConsultaPermissaoMain {

	public static void main(String[] args) {
		
		ApplicationContext app = new SpringApplicationBuilder(
				AlgafoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		PermissaoRepository permissaoRepository = app.getBean(PermissaoRepository.class);
		
		List<Permissao> permissoesList = permissaoRepository.todasPermissoes();
		
		for (Permissao permissao : permissoesList) {
			System.out.printf("%s - %s\n", permissao.getNome(), permissao.getDescricao());
		}
	}
}
