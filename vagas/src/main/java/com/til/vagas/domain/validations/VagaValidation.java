package com.til.vagas.domain.validations;

import com.til.vagas.domain.entities.Vaga;
import com.til.vagas.domain.entities.exceptions.InvalidVagaException;

import java.math.BigDecimal;

public class VagaValidation {

    public static boolean isValid(Vaga vaga) throws InvalidVagaException {


        if (vaga.getTitle() == null || vaga.getTitle().isEmpty()) {
            throw new InvalidVagaException("O título da vaga é nulo ou vazio");
        }
        if (vaga.getTitle().length() > 100) {
            throw new InvalidVagaException("O título da vaga deve ter no máximo 100 caracteres");
        }


        if (vaga.getDescription() != null && vaga.getDescription().length() > 500) {
            throw new InvalidVagaException("A descrição da vaga deve ter no máximo 500 caracteres");
        }


        if (vaga.getSalary() == null || vaga.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidVagaException("O salário da vaga é obrigatório e deve ser maior que zero");
        }


        if (vaga.getLocation() == null || vaga.getLocation().isEmpty()) {
            throw new InvalidVagaException("A localização da vaga é obrigatória");
        }

        return true;
    }

}
