package com.algaworks.algafood;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CozinhaService;
import com.algaworks.algafood.domain.service.RestauranteService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.validation.ConstraintViolationException;
import org.flywaydb.core.Flyway;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource("/application-test.properties")
public class CadastroCozinhaIntegracaoTests {

    private int quanidadeCozinhaCadastrada;
    private Long cozinhaInexistente = 9999L;

    @LocalServerPort
    private int port;

    @Autowired
    private CozinhaService cozinhaService;

    @Autowired
    private CozinhaRepository cozinhaRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    public void setUp(){
        RestAssured.port = this.port;
        basePath = "/cozinhas";

        flyway.migrate();
        quanidadeCozinhaCadastrada = (int) cozinhaRepository.count();
    }


    @Test
    //deveAtribuirId_QuandoCadastrarCozinhaComNome
    public void testeCadastrarCozinhaComNome(){
        //CENÁRIO
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Chinesa");
        //AÇÃO
        cozinha = cozinhaService.salvar(cozinha);

        //VERIFICAÇÃO
        assertNotNull(cozinha);
        assertNotNull(cozinha.getId());
    }

    @Test
    //deveFalhar_QuandoTentarCadastrarCozinhaSemNome
    public void testeCadastrarCozinhaSemNome(){
        //CENÁRIO
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);
        //AÇÃO
        //VERIFICAÇÃO
        assertThrows(ConstraintViolationException.class, ()-> cozinhaService.salvar(cozinha));
    }


    @Test
    public void deveFalhar_quandoTentarExcluirCozinhaEmUso(){
        //CENÁRIO
//        Cozinha cozinha = new Cozinha();
//        cozinha.setNome("Chinesa");
//        cozinha = cozinhaService.salvar(cozinha);
//
//        Restaurante restaurante = new Restaurante();
//        restaurante.setCozinha(cozinha);
//        restaurante.setNome("d");
//        restaurante.setTaxaFrete(new BigDecimal(2));
//        restauranteService.salvar(restaurante);

//        Cozinha finalCozinha = cozinha;
        //AÇÃO
        //VERIFICAÇÃO
        assertThrows(EntidadeEmUsoException.class, ()-> cozinhaService.remover(1L));
    }

    @Test
    public void deveFalhar_QuandoTentarExcluirCozinhaInexistente(){
        //CENÁRIO
        //AÇÃO
        //VERIFICAÇÃO
        assertThrows(CozinhaNaoEncontradaException.class, ()-> cozinhaService.remover(cozinhaInexistente));
    }

    @Test
    public void deveRetornarStatus200_QuandoConsultarCozinhas(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        given()
                .accept(ContentType.JSON)
                .when()
                    .get()
                .then()
                    .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void deveTerCozinhas_QuandoConsultarCozinhas(){
        enableLoggingOfRequestAndResponseIfValidationFails();
        given()
                .accept(ContentType.JSON)
                .when()
                .get()
                .then()
                .body("", Matchers.hasSize(quanidadeCozinhaCadastrada));
    }

    @Test
    public void deveRetornarStatus201_QuandoCadastrarCozinha(){
        given()
                .body("{\"nome\":\"Chinesa\"}")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .when()
                .post()
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }



}
