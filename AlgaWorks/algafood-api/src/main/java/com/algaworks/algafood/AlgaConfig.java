package com.algaworks.algafood;

import org.springframework.context.annotation.Bean;

import com.algaworks.algafood.di.notificacao.NotificadorEmail;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

//@Configuration
public class AlgaConfig {	//CLASSE DE CONFIGURACAO
	//AULA 06 CONFIGURACAO
//    @Bean
//    NotificadorEmail notificadoEmail() {
//		NotificadorEmail notificador = new NotificadorEmail("smtp.algamail.com.br");
//		notificador.setCaixaAlta(true);
//		
//		return notificador;
//	}
//    
//    @Bean
//    AtivacaoClienteService ativacaoCleinteService(){
//    	return new AtivacaoClienteService(notificadoEmail());
//    }
}