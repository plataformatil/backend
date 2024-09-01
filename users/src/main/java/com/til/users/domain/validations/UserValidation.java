package com.til.users.domain.validations;

import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidAddressException;
import com.til.users.domain.exceptions.InvalidUserException;

import java.util.regex.Pattern;

public class UserValidation {
    //Regex do CPF\\ - mr.p
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    //Regex do email\\ - mr.p
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValid(User user) throws InvalidUserException, InvalidAddressException {
        if (user.name == null || user.name.isEmpty()) {
            throw new InvalidUserException("O nome do usuário é nulo ou vazio");
        }
        if (user.name.length() > 20) {
            throw new InvalidUserException("O nome do usuário deve ter no máximo 20 caracteres");
        }
        if (user.email == null || !EMAIL_PATTERN.matcher(user.email).matches()) {
            throw new InvalidUserException("O email do usuário é nulo ou não está no formato adequado");
        }
        if (user.cpf == null || !CPF_PATTERN.matcher(user.cpf).matches()) {
            throw new InvalidUserException("O CPF do usuário é nulo ou não está no formato adequado");
        }
        if (user.roles == null) {
            throw new InvalidUserException("O role do usuário é nulo");
        }
        try {
            AddressValidation.isValid(user.address);
        } catch (InvalidAddressException ex) {
            throw new InvalidUserException("O endereço está com problemas: " + ex.getMessage());
        }
        return true;
    }
}
