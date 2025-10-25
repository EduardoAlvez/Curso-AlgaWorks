package com.algaworks.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemaTipo {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada","entidade nao encontrada."),
    ENTIDADE_EM_USO("/entidade-em-uso","entidade esta em uso."),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem esta com algo errado, verifique."),
    NEGOCIO("/regra-de-negocio","regra de negocio violada.");


    public String titulo;
    public String uri;

    ProblemaTipo(String path, String titulo){
        this.titulo = titulo;
        this.uri = "localhost:8080" + path;
    }


}
