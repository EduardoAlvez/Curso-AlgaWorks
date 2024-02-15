package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadoProperties {

	/**
	 * Host do servidor de Email
	 */
	private String hostServidor;
	
	/**
	 * Host do servidor de porta
	 */
	private Integer portaServidor = 2525;
	
	public String getHostServidor() {
		return hostServidor;
	}
	public Integer getPortaServidorInteger() {
		return portaServidor;
	}
	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}
	public void setPortaServidorInteger(Integer portaServidorInteger) {
		this.portaServidor = portaServidorInteger;
	}
	
	
}
