package com.algaworks.algafood.api.model.dtos;

import com.algaworks.algafood.domain.model.Cozinha;
import lombok.Data;

@Data
public class RestauranteModel {

    private Long id;
    private String nome;
    private CozinhaModel cozinha;

}
