package com.algaworks.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;

    private String titulo;
    private String tipo;
    private String detalhe;
    private String mensagemUsuario;
    private List<objeto> objetos;

    @Getter
    @Builder
    public static class objeto {
        private String name;
        private String mensagemUsuario;
    }
}
