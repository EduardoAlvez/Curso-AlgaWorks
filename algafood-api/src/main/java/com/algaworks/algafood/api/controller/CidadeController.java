package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.service.CidadeService;

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
	public ResponseEntity<?> buscar(@PathVariable("cidadeId") Long id) {
		
		Optional<Cidade> cidadeAtual = cidadeService.buscarPorId(id);
		
		if(cidadeAtual.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cidadeAtual);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();	
}
	
	
	@PostMapping
	public ResponseEntity<Cidade> adicionar(@RequestBody Cidade cidade) {
		Cidade cidadeAtual = cidadeService.salvar(cidade);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeAtual);
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> atualizar(@PathVariable("cidadeId") Long id, @RequestBody Cidade cidade) {
		
		Optional<Cidade> cidadeAtual = cidadeService.buscarPorId(id);
		
		if (cidadeAtual.isPresent()) {
			BeanUtils.copyProperties(cidade, cidadeAtual.get(), "id");
//			Cidade cidadeAtualizada = cidadeService.salvar(cidadeAtual.get());
			
			try {
				return ResponseEntity.status(HttpStatus.OK).body(cidadeService.salvar(cidadeAtual.get()));
				
			} catch (EntidadeNaoEncontradaException e) {
				
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable("cidadeId") Long id) {
		try {
			cidadeService.remover(id);
			
			return ResponseEntity.noContent().build();
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
