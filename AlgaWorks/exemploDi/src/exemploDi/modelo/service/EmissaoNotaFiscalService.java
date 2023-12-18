package exemploDi.modelo.service;

import exemploDi.modelo.Cliente;
import exemploDi.modelo.Produto;
import exemploDi.modelo.notificacao.Notificador;

public class EmissaoNotaFiscalService {

	private Notificador notificador;

	public EmissaoNotaFiscalService(Notificador notificador) {
		// TODO Auto-generated constructor stub
		this.notificador = notificador;

	}
	// USA A INTERFACE COMO CONTRATO, AGORA ELE NAO MANDA UM TIPO ESPECÍFICO...;
	public void emitir(Cliente cliente, Produto produto) {
		// TODO emitir nota fiscal aqui...

		this.notificador.notificar(cliente, "Nota fiscal do produto " + produto.getNome() + " foi emitida!");
	}
}
