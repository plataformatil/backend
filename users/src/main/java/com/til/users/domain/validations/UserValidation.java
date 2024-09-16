package com.til.users.domain.validations;

import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidAddressException;
import com.til.users.domain.exceptions.InvalidPasswordException;
import com.til.users.domain.exceptions.InvalidUserException;
import com.til.users.infraestructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.regex.Pattern;

public class UserValidation {
    //Regex do CPF\\ - mr.p
    private static final Pattern CPF_PATTERN = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    //Regex do CNPJ\\ - mr.p
    private static final Pattern CNPJ_PATTERN = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");
    //Regex do email\\ - mr.p
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static boolean isValid(User user, IUserRepository repository) throws InvalidUserException {
        var dataUser = repository.findAll();

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new InvalidUserException("O nome do usuário é nulo ou vazio");
        }
        if (user.getName().length() > 20) {
            throw new InvalidUserException("O nome do usuário deve ter no máximo 20 caracteres");
        }
        for (User existingUser : dataUser) {
            if (existingUser.getName().equals(user.getName())) {
                throw new InvalidUserException("O nome de usuário ja existe");
            }
        }
        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new InvalidUserException("O email do usuário é nulo ou não está no formato adequado");
        }
        for (User existingUser : dataUser) {
            if (existingUser.getEmail().equals(user.getEmail())) {
                throw new InvalidUserException("O email de usuário já existe");
            }
        }
        try {
            PasswordValidation.isValid(user.getPassword());
        } catch (InvalidPasswordException ex) {
            throw new InvalidUserException("A senha é inválida: " + ex.getMessage());
        }
        if (user.getCpf() != null && !CPF_PATTERN.matcher(user.getCpf()).matches()) {
            throw new InvalidUserException("O CPF do usuário é nulo ou não está no formato adequado");
        }
        if (user.getCnpj() != null && !CNPJ_PATTERN.matcher(user.getCnpj()).matches()) {
            throw new InvalidUserException("O CNPJ do usuário é nulo ou não está no formato adequado");
        }
        if (user.getCpf() == null && user.getCnpj() == null) {
            throw new InvalidUserException("CPF e CNPJ não podem ser nulos");
        }
        if (user.getCpf() != null && user.getCnpj() != null) {
            throw new InvalidUserException("Um usuário deve ter um CPF ou um CNPJ");
        }
        if (user.getRoles() == null) {
            throw new InvalidUserException("O role do usuário é nulo");
        }
        try {
            AddressValidation.isValid(user.getAddress());
        } catch (InvalidAddressException ex) {
            throw new InvalidUserException("O endereço é inválido: " + ex.getMessage());
        }
        return true;
    }
}
