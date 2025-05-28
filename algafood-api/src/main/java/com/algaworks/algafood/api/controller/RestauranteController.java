package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

//	@Autowired
//	private RestauranteRepository restauranteRepository;
	@Autowired
	private RestauranteService restauranteService;
	
	//MÉTODOS
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteService.buscarTodos();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<?> buscar(@PathVariable("restauranteId") Long id){
		Optional<Restaurante> restaurante = restauranteService.buscarPorID(id);
		
		if(restaurante.isPresent())
			return ResponseEntity.status(HttpStatus.OK).body(restaurante.get());
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable("restauranteId") Long id, @RequestBody Restaurante restaurante){
		
		Optional<Restaurante> restauranteAtual = restauranteService.buscarPorID(id);
		
		if(restauranteAtual.isPresent()) {
			BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id,", "produtos");
			
			try {
//				restauranteAtual = restauranteService.salvar(restauranteAtual);
				return ResponseEntity.ok(restauranteService.salvar(restauranteAtual.get()));
				
			} catch (EntidadeNaoEncontradaException e) {
				
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial (@PathVariable("restauranteId") Long id, @RequestBody Map<String, Object> dados){
		//BUSCA O RESTAURANTE ORIGIAL
		Optional<Restaurante> restauranteDestino = restauranteService.buscarPorID(id);
		
		// Verifica se tem algum objeto, caso nao joga uma msg de erro
		if (!restauranteDestino.isPresent()) 
			return ResponseEntity.notFound().build();
		
		mesclar(dados, restauranteDestino.get());
		return atualizar(id, restauranteDestino.get());
	}

	private void mesclar(Map<String, Object> dados, Restaurante restauranteDestino) {
		//MAPEA AS VARIAVES E CONVERTE OS DADOS PARA SE IGUAL A DA CLASSE "RESTAURANTE"
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dados, Restaurante.class);
	
		dados.forEach((nomeDoDado, valorDoDado)->{
			//BUSCA NA CLASSE "RESTAURANTE" UMA VARIVEL DE MESMO NOME QUE FOI PASSADA
			Field field = ReflectionUtils.findField(Restaurante.class, nomeDoDado);
			field.setAccessible(true);
			
//			NÃO PODEMOS PASSAR O VALOR DA VARIÁVEL DIRETO, POIS, PODE OCORRER ERROS, TEMOS QUE TRATAR PRIMEIRO 
//			PARA ASSIM PASSAR OS DADOS SEM NENHUM ERRO
			
			//BUSCA UM VALOR IQUAL AO QUE FOI PASSADO
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			//PARA CADA CAMPO BUSCADO ELE TROCA PELO DADO NOVO QUE FOI ALTERADO
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
			
		});	
	}
	
	
	@DeleteMapping("/{restauranteId}")
	public ResponseEntity<?> remover(@PathVariable("restauranteId") Long id){
		
		try {
			restauranteService.remover(id);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	
	}
}
