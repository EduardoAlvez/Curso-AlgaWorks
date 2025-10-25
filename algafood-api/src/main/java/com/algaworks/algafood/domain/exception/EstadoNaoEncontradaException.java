package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradaException extends EntidadeNaoEncontradaException {
    public EstadoNaoEncontradaException(String message) {
        super(message);
    }

    public EstadoNaoEncontradaException(Long id){
        this(String.format("Nao existe um cadastro com Estado com o codigo %d .", id));
    }
}
