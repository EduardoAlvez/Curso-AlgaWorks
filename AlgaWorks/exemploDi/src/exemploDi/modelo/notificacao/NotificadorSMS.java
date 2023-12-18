package exemploDi.modelo.notificacao;

import exemploDi.modelo.Cliente;

public class NotificadorSMS implements Notificador {
	
	@Override
	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s por SMS através do telefone %s: %s\n",
					cliente.getNome(), cliente.getTele(), mensagem);
	}

}
