package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService cidadeService;
	
	@GetMapping
	public List<Cidade> listar(){
		return cidadeService.todos();
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long id) {
		return  cidadeService.buscarOuFalhar(id).get();
}
	
	
	@PostMapping
	public ResponseEntity<Cidade> adicionar(@Valid @RequestBody Cidade cidade) {
		Cidade cidadeAtual = cidadeService.salvar(cidade);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeAtual);
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable("cidadeId") Long id, @Valid @RequestBody Cidade cidade) {
		try {
			Optional<Cidade> cidadeAtual = cidadeService.buscarOuFalhar(id);
			BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
			return ResponseEntity.status(HttpStatus.OK).body(cidadeService.salvar(cidadeAtual.get()));
		} catch (EstadoNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("cidadeId") Long id) {
		cidadeService.remover(id);
	}


//	@ExceptionHandler(EntidadeNaoEncontradaException.class)
//	public ResponseEntity<?> tratarEntidadeNaoEncontraException(EntidadeNaoEncontradaException e){
//		Problema problema = Problema.builder()
//				.hora(LocalDateTime.now())
//				.mensagem(e.getMessage())
//				.build();
//
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);
//	}
}
