package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

@Controller
@RequestMapping
public class MeuPrimeiroController {

	private final AtivacaoClienteService ativacaoClienteService;

	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MeuPrimeiroController:"+ ativacaoClienteService);
	}

	@GetMapping("/hello")
	@ResponseBody
	public String DonaNandinhaV2() {

		Cliente eduardo = new Cliente("Eduardo", "dudu.200910@hotmail.com", "8199956-4050");
		ativacaoClienteService.Ativar(eduardo);

		return "Fernadinha Beira mar";
	}

}
