package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@TipoDoNotificador(NivelDeUrgencia.SEM_URGENCIA)
@Component //bean
public class NotificadorEmail implements Notificador  {
	
	@Autowired
	private NotificadoProperties properties;
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s\n",
				cliente.getNome(), cliente.getEmail(),this.properties.getHostServidor(), mensagem); 
	}
}

