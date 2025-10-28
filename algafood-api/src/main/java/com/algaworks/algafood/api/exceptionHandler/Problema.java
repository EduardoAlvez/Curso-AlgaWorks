package com.algaworks.algafood.api.exceptionHandler;

import com.fasterxml.jackson.annotation.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problema {

    private Integer status;

    private String titulo;
    private String tipo;
    private String detalhe;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timeStamp;
    private String mensagemUsuario;
}
