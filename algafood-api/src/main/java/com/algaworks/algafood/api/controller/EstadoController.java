package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.EstadoNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import jakarta.validation.Valid;
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
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@Valid @RequestBody Estado estado){
		try {
			return estadoService.salvar(estado);
		}catch (EstadoNaoEncontradaException e){
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<?> atualizar(@PathVariable("estadoId") Long id, @Valid @RequestBody Estado estado){
		Estado estadoAtual = estadoService.buscarOuFalhar(id);
		BeanUtils.copyProperties(estado, estadoAtual, "id");

		return ResponseEntity.status(HttpStatus.OK).body(estadoService.salvar(estadoAtual));
		}
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("estadoId") Long id) {
		estadoService.remover(id);
	}
}
