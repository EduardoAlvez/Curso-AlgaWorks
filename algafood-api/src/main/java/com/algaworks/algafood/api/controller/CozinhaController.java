package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
		return cadastrarCozinhaService.todas();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXml() {
		return new CozinhasXmlWrapper(cadastrarCozinhaService.todas());
	}

//	PODE SER USADO PRA IGNORAR OS DADOS QUE SERÁ EXIBIDO
//	@JsonIgnore

//	PODE SER USADO PRA MODIFICA DADOS QUE SERÁ EXIBIDO
//	@JsonProperty

//	DIFERENCIA OS GET E COMPLEMENTA O MAPPING DA CLASSE
//	@ResponseStatus(value = HttpStatus.OK) RETORNA UMA MESANGEM DE STATUS HTTP, PODEMOS FAZER DE OUTRA FORMAS, MOSTRADA LOGO ABAIXO

	@GetMapping("/{cozinhaId}")
	public Cozinha buscar(@PathVariable("cozinhaId") Long id) {// @PathVariable DIZ ONDE ASSOCIAR O ID
		return cadastrarCozinhaService.buscarOuFalhar(id);

//		return cozinhaRepository.findById(id).orElseThrow(()-> new EntidadeNaoEncontradaException("TESTE"));

//		Optional<Cozinha> cozinha = cozinhaRepository.findById(id); // ADAPTANDO TUDO PARA USO DE OPTIONAL!!
//		if (cozinha.isPresent())
//			return ResponseEntity.status(HttpStatus.OK).body(cozinha.get()); // DUAS FORMA SIMPLES DE MODIFCAR O STATUS HTTP. PRIMEIRA FORMA
																		
//		return ResponseEntity.ok(cozinha); SEGUNDA FORMA. LOGO CONHECEMOS TRES FORMAS DIFERENTE DE MODIFICA O STATUS HTTP

//		return ResponseEntity.notFound().build();

//		COMO FUNCIONA UM STATUS 302
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.LOCATION, "http://api.algafood.local:8080/cozinhas");		
//		return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cozinha adicionar(@Valid @RequestBody Cozinha cozinha) { // @RequestBody PEGA A INFORMAÇÃO E TRANSFORMA SEQUINDO AS
																// CARACTERISCA DA CLASSE
		return cadastrarCozinhaService.salvar(cozinha);
	}

	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") Long id, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

		if (cozinhaAtual.isPresent()) {
			BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
//			cozinhaAtual = cadastrarCozinhaService.salvar(cozinhaAtual);
			
			return ResponseEntity.ok(cadastrarCozinhaService.salvar(cozinhaAtual.get()));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cozinhaId") Long id) {
		cadastrarCozinhaService.remover(id);
	}

}
