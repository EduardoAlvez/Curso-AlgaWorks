package exemploDi.modelo.notificacao;

import exemploDi.modelo.Cliente;

public interface Notificador {

	void notificar(Cliente cliente, String mensagem);
}
