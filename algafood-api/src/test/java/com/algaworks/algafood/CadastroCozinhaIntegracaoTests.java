package com.algaworks.algafood;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
public class CadastroCozinhaIntegracaoTests {

    @Autowired
    private CozinhaService cozinhaService;

    @Test
    public void testeCadastrarCozinhaComNome(){
        //CENÁRIO
        Cozinha cozinha = new Cozinha();
        cozinha.setNome("Chinesa");
        //AÇÃO
        cozinha = cozinhaService.salvar(cozinha);

        //VERIFICAÇÃO
        Assertions.assertNotNull(cozinha);
        Assertions.assertNotNull(cozinha.getId());
    }

    @Test
    public void testeCadastrarCozinhaSemNome(){
        //CENÁRIO
        Cozinha cozinha = new Cozinha();
        cozinha.setNome(null);
        //AÇÃO
        //VERIFICAÇÃO
        Assertions.assertThrows(ConstraintViolationException.class, ()-> cozinhaService.salvar(cozinha));
    }
}
