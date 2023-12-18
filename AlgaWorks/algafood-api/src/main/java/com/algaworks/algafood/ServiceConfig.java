package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Configuration
public class ServiceConfig {
	//AULA 06 CONFIGURACAO
//    @Bean	//Bean sao gerenciado pelo Spring
//    AtivacaoClienteService ativacaoCleinteService(Notificador notificador){	//O SPRING SABE DE ONDE PEGAR
//    	return new AtivacaoClienteService(notificador);
//    }
}
