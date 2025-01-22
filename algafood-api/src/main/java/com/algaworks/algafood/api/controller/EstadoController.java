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

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@GetMapping
	public List<Estado> listar(){
		return estadoService.todos();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId") Long id){
		Optional<Estado> estado = estadoService.buscarPorId(id);
		
		if(estado.isPresent()) {
			return ResponseEntity.ok(estado.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		Estado estadoAtual = estadoService.salvar(estado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoAtual);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") Long id, @RequestBody Estado estado){
		Optional<Estado> estadoAtual = estadoService.buscarPorId(id);
		
		if(estadoAtual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoAtual.get(), "id");
			Estado estadoAatualizado = estadoService.salvar(estadoAtual.get());
				
			return ResponseEntity.ok(estadoAatualizado);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable("estadoId") Long id) {
		try {
			estadoService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
