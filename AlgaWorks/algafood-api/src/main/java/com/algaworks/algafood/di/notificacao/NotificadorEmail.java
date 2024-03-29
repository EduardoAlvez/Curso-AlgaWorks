package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

@Qualifier("normal")
@Component //bean
public class NotificadorEmail implements Notificador  {
	
	private String hostServidorSmtp;
	
//	private boolean caixaAlta;
//	AULA 06 @CONFIGURACAO
//	public NotificadorEmail(String hostServidorSmtp){
//		this.hostServidorSmtp = hostServidorSmtp;
//	}
	 
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		//AULA 06 @CONFIGURACAO
//		if(this.caixaAlta) {
//			mensagem = mensagem.toUpperCase();
//		}
		
		System.out.printf("Notificando %s através do e-mail %s usando SMTP %s: %s\n",
				cliente.getNome(), cliente.getEmail(),this.hostServidorSmtp, mensagem); 
	}
	//AULA 06 @CONFIGURACAO
//	public void setCaixaAlta(boolean caixaAlta) {
//		this.caixaAlta = caixaAlta;
//	}
	
	
	
}

