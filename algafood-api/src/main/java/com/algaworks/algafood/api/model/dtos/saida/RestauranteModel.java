package com.algaworks.algafood.api.model.dtos.saida;

import lombok.Data;

@Data
public class RestauranteModel {

    private Long id;
    private String nome;
    private CozinhaModel cozinha;

}
