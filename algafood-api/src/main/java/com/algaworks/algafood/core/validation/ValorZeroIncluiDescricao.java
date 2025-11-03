package com.algaworks.algafood.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.OverridesAttribute;
import jakarta.validation.Payload;
import jakarta.validation.constraints.PositiveOrZero;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {ValorZeroIncluiDescricaoValidator.class}
)
public @interface ValorZeroIncluiDescricao {

    String message() default "A descrição obrigatória inválida.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String valor();
    String descricao();
    String descricaoObrigatoria();
}
