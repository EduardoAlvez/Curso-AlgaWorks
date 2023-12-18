package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.di.notificacao.NotificadorEmail;

@Configuration
public class NotificadorConfig {
	//AULA 06 CONFIGURACAO
//    @Bean
//    NotificadorEmail notificadoEmail() {
//		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
//		notificador.setCaixaAlta(true);
//		
//		return notificador;
//	}
}
