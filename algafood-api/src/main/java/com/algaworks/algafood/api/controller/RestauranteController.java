package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.model.dtos.entrada.CozinhaIDInputModel;
import com.algaworks.algafood.api.model.dtos.entrada.RestauranteInputModel;
import com.algaworks.algafood.api.model.dtos.saida.CozinhaModel;
import com.algaworks.algafood.api.model.dtos.saida.RestauranteModel;
import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.RestauranteService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

//	@Autowired
//	private RestauranteRepository restauranteRepository;
	@Autowired
	private RestauranteService restauranteService;

    @Autowired
    private SmartValidator smartValidator;
	
	//MÉTODOS
	
	@GetMapping
	public List<RestauranteModel> listar(){
		return toCollertionModel(restauranteService.buscarTodos());
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<RestauranteModel> buscar(@PathVariable("restauranteId") Long id){
		Restaurante restaurante = restauranteService.buscarOuFalhar(id);

		return ResponseEntity.status(HttpStatus.OK).body(toModel(restaurante));
	}
	
	@PostMapping
	public ResponseEntity<RestauranteModel> adicionar(@Valid @RequestBody RestauranteInputModel restaurante) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(toModel(restauranteService.salvar(toDomain(restaurante))));
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
	
	@PutMapping("/{restauranteId}")
	public ResponseEntity<RestauranteModel> atualizar(@PathVariable("restauranteId") Long id, @Valid @RequestBody RestauranteInputModel restauranteInput) {
        Restaurante restauranteAtual;
        try {
            Restaurante restaurante = toDomain(restauranteInput);
            restauranteAtual = restauranteService.buscarOuFalhar(id);

            BeanUtils.copyProperties(restaurante, restauranteAtual,
                    "id", "produtos", "dataCadastro", "formasPagamento", "dataCadastro", "endereco");
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }

        return ResponseEntity.ok(toModel(restauranteService.salvar(restauranteAtual)));
    }
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<RestauranteModel> atualizarParcial (@PathVariable("restauranteId") Long id, @RequestBody Map<String, Object> dados, HttpServletRequest request){
		//BUSCA O RESTAURANTE ORIGIAL
		Restaurante restauranteDestino = restauranteService.buscarOuFalhar(id);
		
		mesclar(dados, restauranteDestino, request);
        validar(restauranteDestino, "Restaurante");

		RestauranteInputModel restauranteInputModel = new RestauranteInputModel();
		restauranteInputModel.setNome(restauranteDestino.getNome());
		restauranteInputModel.setTaxaFrete(restauranteDestino.getTaxaFrete());

		CozinhaIDInputModel cozinha = new CozinhaIDInputModel();
		cozinha.setId(restauranteDestino.getCozinha().getId());

		restauranteInputModel.setCozinha(cozinha);

		return atualizar(id, restauranteInputModel);
	}

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable("restauranteId") Long id){
		restauranteService.remover(id);

	}

	//------ MÉTODOS PRIVADOS
	private void validar(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante,
				objectName);
		smartValidator.validate(restaurante, bindingResult);

		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
	}

	private Restaurante toDomain(RestauranteInputModel restauranteInputModel){
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInputModel.getNome());
		restaurante.setTaxaFrete(restauranteInputModel.getTaxaFrete());

		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInputModel.getCozinha().getId());

		restaurante.setCozinha(cozinha);
		return restaurante;
	}

	private RestauranteModel toModel(Restaurante restaurante) {
		RestauranteModel restauranteModel = new RestauranteModel();
		CozinhaModel cozinhaModel = new CozinhaModel();

		cozinhaModel.setId(restaurante.getCozinha().getId());
		cozinhaModel.setNome(restaurante.getCozinha().getNome());

		restauranteModel.setId(restaurante.getId());
		restauranteModel.setNome(restaurante.getNome());
		restauranteModel.setTaxaFrete(restaurante.getTaxaFrete());
		restauranteModel.setCozinha(cozinhaModel);

		return restauranteModel;
	}

	private List<RestauranteModel> toCollertionModel(List<Restaurante> restaurantes){
		return restaurantes.stream().map(this::toModel).collect(Collectors.toList());
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
}
