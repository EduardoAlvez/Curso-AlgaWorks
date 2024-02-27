package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

//@Controller
//@ResponseBody
//@JsonRootName("gastronomia") MUDA O VALOR BRUTO QUE SERÁ EXIBIDO
@RestController
@RequestMapping("/cozinhas") // REQUISIÇOES QUE VAO CAIR AQUI
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
//	METODO QUE VAI SER CHAMADO NO GET.
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.todasCozinhas();
	}
	
	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(cozinhaRepository.todasCozinhas());
	}
	
//	PODE SER USADO PRA IGNORAR OS DADOS QUE SERÁ EXIBIDO
//	@JsonIgnore
//	PODE SER USADO PRA MODIFICA UM DADO QUE SERÁ EXIBIDO
//	@JsonProperty
//	DIFERENCIA OS GET E COMPLEMENTA O MAPPING DA CLASSE
//	@ResponseStatus(value = HttpStatus.OK) RETORNA UMA MESANGEM DE STATUS HTTP, PODEMOS FAZER DE OUTRA FORMAS, MOSTRADA LOGO ABAIXO
	@GetMapping("/{cozinhaId}")
//	@PathVariable DIZ ONDE ASSOCIAR O ID
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		Cozinha cozinha = cozinhaRepository.porIDCozinha(id);
		
		if(cozinha != null)
			return ResponseEntity.status(HttpStatus.OK).body(cozinha); //DUAS FORMA SIMPLES DE MODIFCAR O STATUS HTTP PRIMEIRA FORMA
//		return ResponseEntity.ok(cozinha); SEGUNDA FORMA. LOGO CONHECEMOS TRES FORMAS DIFERENTE DE MODIFICA O STATUS HTTP
		
		return ResponseEntity.notFound().build();
		
		
//		COMO FUNCIONA UM STATUS 302
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/cozinhas");		
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}
}
