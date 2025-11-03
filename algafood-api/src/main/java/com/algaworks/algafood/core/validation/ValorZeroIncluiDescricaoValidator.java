package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public class ValorZeroIncluiDescricaoValidator implements ConstraintValidator<ValorZeroIncluiDescricao, Object> {


    private String descricao;
    private String descricaoObrigatoria;
    private String valor;

    @Override
    public void initialize(ValorZeroIncluiDescricao constraintAnnotation) {
        this.descricao = constraintAnnotation.descricao();
        this.descricaoObrigatoria = constraintAnnotation.descricaoObrigatoria();
        this.valor = constraintAnnotation.valor();

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;

        try {
            BigDecimal valorValidado = (BigDecimal) BeanUtils.getPropertyDescriptor(o.getClass(), this.valor)
                    .getReadMethod().invoke(o);

            String nomeValidado = (String) BeanUtils.getPropertyDescriptor(o.getClass(), descricao)
                    .getReadMethod().invoke(o);

            if (valorValidado != null && nomeValidado != null
                    && BigDecimal.ZERO.compareTo(valorValidado) == 0) {
                valido = nomeValidado.toLowerCase().contains(descricaoObrigatoria.toLowerCase());

            }
            return valido;

        } catch (Exception e) {
            throw new RuntimeException(e);

        }

    }
}
