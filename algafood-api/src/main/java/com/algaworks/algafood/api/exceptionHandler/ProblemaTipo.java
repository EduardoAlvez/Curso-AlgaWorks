package com.algaworks.algafood.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemaTipo {
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade esta em uso."),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel","Mensagem esta com algo errado, verifique."),
    NEGOCIO("/regra-de-negocio","Regra de negocio violada."),
    PARAMETRO_NAO_SUPORTADO("/tipo-nao-suportado","O tipo inserido nao e suportado, verifique a chamada do metodo."),
    RECURSO_NAO_ENCONTRADA("/recurso-nao-encontrada","Recurso nao encontrada."),
    ERRO_DO_SISTEMA("/erro-do-sistema","Aconteceu algo inesperado."),
    DADOS_INVALIDOS("/dados-invalidos","Dados invalidos");




    public String titulo;
    public String uri;

    ProblemaTipo(String path, String titulo){
        this.titulo = titulo;
        this.uri = "localhost:8080" + path;
    }


}
