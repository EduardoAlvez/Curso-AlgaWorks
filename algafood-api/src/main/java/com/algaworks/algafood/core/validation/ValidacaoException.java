package com.algaworks.algafood.core.validation;

import lombok.Getter;
import org.springframework.validation.BindingResult;


@Getter
public class ValidacaoException extends RuntimeException{

    private final BindingResult bindingResult;

    public ValidacaoException(BindingResult bindingResult){
        this.bindingResult = bindingResult;
    }

}
