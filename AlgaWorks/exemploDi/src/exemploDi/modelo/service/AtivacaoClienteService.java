package exemploDi.modelo.service;

import exemploDi.modelo.Cliente;
import exemploDi.modelo.notificacao.Notificador;

public class AtivacaoClienteService {

	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
		this.notificador = notificador;
	}
	
	public void Ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo.");
	}
	
}
