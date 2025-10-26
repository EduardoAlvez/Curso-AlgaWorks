package com.algaworks.algafood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

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
		Restaurante restaurante = restauranteService.buscarOuFalhar(id);
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			restaurante = restauranteService.salvar(restaurante);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<?> atualizar(@PathVariable("restauranteId") Long id, @RequestBody Restaurante restaurante){

        Restaurante restauranteAtual = null;
        try {
            restauranteAtual = restauranteService.buscarOuFalhar(id);
            BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id", "produtos", "dataCadastro","formasPagamento", "dataCadastro", "endereco");
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

        return ResponseEntity.ok(restauranteService.salvar(restauranteAtual));
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial (@PathVariable("restauranteId") Long id, @RequestBody Map<String, Object> dados, HttpServletRequest request){
		//BUSCA O RESTAURANTE ORIGIAL
		Restaurante restauranteDestino = restauranteService.buscarOuFalhar(id);
		
		mesclar(dados, restauranteDestino, request);
		return atualizar(id, restauranteDestino);
	}

	private void mesclar(Map<String, Object> dados, Restaurante restauranteDestino, HttpServletRequest request) {
		ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);

		//MAPEA AS VARIAVES E CONVERTE OS DADOS PARA SE IGUAL A DA CLASSE "RESTAURANTE"
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

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
		} catch (IllegalArgumentException e) {
			Throwable rootCause = ExceptionUtils.getRootCause(e);
			throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
        }
    }
	
	
	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("restauranteId") Long id){
		restauranteService.remover(id);

	}
}
