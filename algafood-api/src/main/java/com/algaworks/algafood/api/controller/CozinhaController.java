package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.CozinhasXmlWrapper;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;

//@Controller
//@ResponseBody
//@JsonRootName("gastronomia") MUDA O VALOR BRUTO QUE SERÁ EXIBIDO
@RestController
@RequestMapping("/cozinhas") // REQUISIÇOES QUE VAO CAIR AQUI
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaService cadastrarCozinhaService;

//	METODO QUE VAI SER CHAMADO NO GET.
	@GetMapping
	public List<Cozinha> listar() {
		return cozinhaRepository.todas();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(cozinhaRepository.todas());
	}

//	PODE SER USADO PRA IGNORAR OS DADOS QUE SERÁ EXIBIDO
//	@JsonIgnore

//	PODE SER USADO PRA MODIFICA DADOS QUE SERÁ EXIBIDO
//	@JsonProperty

//	DIFERENCIA OS GET E COMPLEMENTA O MAPPING DA CLASSE
//	@ResponseStatus(value = HttpStatus.OK) RETORNA UMA MESANGEM DE STATUS HTTP, PODEMOS FAZER DE OUTRA FORMAS, MOSTRADA LOGO ABAIXO

	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {// @PathVariable DIZ ONDE ASSOCIAR O ID
		Cozinha cozinha = cozinhaRepository.buscarPorId(id);

		if (cozinha != null)
			return ResponseEntity.status(HttpStatus.OK).body(cozinha); // DUAS FORMA SIMPLES DE MODIFCAR O STATUS HTTP
																		// PRIMEIRA FORMA
//		return ResponseEntity.ok(cozinha); SEGUNDA FORMA. LOGO CONHECEMOS TRES FORMAS DIFERENTE DE MODIFICA O STATUS HTTP

		return ResponseEntity.notFound().build();

//		COMO FUNCIONA UM STATUS 302
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/cozinhas");		
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adicionar(@RequestBody Cozinha cozinha) { // @RequestBody PEGA A INFORMAÇÃO E TRANSFORMA SEQUINDO AS
																// CARACTERISCA DA CLASSE
		return cadastrarCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha) {
		Cozinha cozinhaAtual = cozinhaRepository.buscarPorId(id);

		if (cozinhaAtual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
			cozinhaAtual = cadastrarCozinhaService.salvar(cozinhaAtual);

			return ResponseEntity.ok(cozinhaAtual);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> remover(@PathVariable("cozinhaId") Long id) {
		try {
			cadastrarCozinhaService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
