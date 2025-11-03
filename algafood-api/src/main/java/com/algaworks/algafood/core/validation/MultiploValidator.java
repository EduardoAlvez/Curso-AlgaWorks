package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numeroMultiplo;

    // PREPARA A INSTANCIA
    @Override
    public void initialize(Multiplo constraintAnnotation) {
        this.numeroMultiplo = constraintAnnotation.numero();
    }

    // FAZ A VERIFICAÇAO
    @Override
    public boolean isValid(Number number, ConstraintValidatorContext constraintValidatorContext) {
        boolean valido = true;
        if (number != null) {
            BigDecimal multiplo = BigDecimal.valueOf(number.doubleValue());
            BigDecimal multiplo2 = BigDecimal.valueOf(this.numeroMultiplo);
            var resto = multiplo.remainder(multiplo2);

            valido = BigDecimal.ZERO.compareTo(resto) == 0;
        }

        return valido;
    }
}
