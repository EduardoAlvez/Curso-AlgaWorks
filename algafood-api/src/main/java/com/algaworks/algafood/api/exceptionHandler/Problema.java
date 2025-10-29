package com.algaworks.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.lang.reflect.Field;
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
    private List<Campo> campos;

    @Getter
    @Builder
    public static class Campo {
        private String name;
        private String mensagemUsuario;
    }
}
