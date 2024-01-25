package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {
	
	@Qualifier("normal")
	@Autowired //(required = false) PONTO DE INJECAO, FUNCIONA MESMO PRIVADO
	private Notificador notificador;	

	public void Ativar(Cliente cliente) {
		cliente.ativar();
		
		if(notificador != null) {
			notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");			
		}else {
			System.out.println("Não existe notificador, mas cliente foi ativado");
		}
	}

//	 CONSTRUTORES SAO PONTOS DE INJECAO
//	@Autowired
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
//	public AtivacaoClienteService(String blablaString) {
//	}

//	@Autowired // PONTO DE INJECAO
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}

}
