package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public Estado buscar(@PathVariable("estadoId") Long id){
		return estadoService.buscarOuFalhar(id);
	}
	
	@PostMapping
	public ResponseEntity<Estado> adicionar(@RequestBody Estado estado){
		Estado estadoAtual = estadoService.salvar(estado);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoAtual);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") Long id, @RequestBody Estado estado){
		Estado estadoAtual = estadoService.buscarOuFalhar(id);
		
		BeanUtils.copyProperties(estado, estadoAtual, "id");
		Estado estadoAatualizado = estadoService.salvar(estadoAtual);
				
		return ResponseEntity.ok(estadoAatualizado);
		}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("estadoId") Long id) {
		estadoService.remover(id);
	}
}
