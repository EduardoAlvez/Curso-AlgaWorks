package exemploDi.di;

import exemploDi.modelo.Cliente;
import exemploDi.modelo.notificacao.Notificador;
import exemploDi.modelo.notificacao.NotificadorSMS;
import exemploDi.modelo.service.AtivacaoClienteService;

public class Main {

	public static void main(String[] args) {
		Cliente c1 = new Cliente("João", "Joao@email.com","8199944-3233");
		Cliente c2 = new Cliente("Maria", "Maria@email.com","8199943-2322");
		
		//	FACILITA TESTE UNITARIOS, CRIANDO MOCK?
		Notificador notificador = new NotificadorSMS();
//		Notificador notificador = new NotificadorSMS();
		
		AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService(notificador);
		ativacaoClienteService.Ativar(c2);
		ativacaoClienteService.Ativar(c1);
		
	}
}
