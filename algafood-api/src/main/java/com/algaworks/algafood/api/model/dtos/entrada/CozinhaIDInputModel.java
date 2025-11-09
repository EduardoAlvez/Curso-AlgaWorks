package com.algaworks.algafood.api.model.dtos.entrada;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CozinhaIDInputModel {

    @NotNull
    private Long id;
}
