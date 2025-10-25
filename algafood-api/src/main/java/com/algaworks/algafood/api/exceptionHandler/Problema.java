package com.algaworks.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@JsonInclude
@Getter
@Builder
public class Problema {

    private Integer status;

    private String titulo;
    private String tipo;
    private String detalhe;

//    private LocalDateTime hora;
//    private String mensagem;
}
