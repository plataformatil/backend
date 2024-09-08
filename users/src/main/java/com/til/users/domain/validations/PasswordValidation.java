package com.til.users.domain.validations;

import com.til.users.domain.exceptions.InvalidPasswordException;

import java.util.regex.Pattern;

public class PasswordValidation {
    //Regex para verificar se tem ao menos 1 número\\ - mr.p
    private static final Pattern HAS_NUMBER = Pattern.compile(".*\\d.*");

    //Regex para verificar se tem ao menos 1 caractere especial\\ - mr.p
    private static final Pattern HAS_SPECIAL_CHAR = Pattern.compile(".*[@$.#%!%\\-_?&].*");

    //Regex para verificar se tem ao menos uma letra maiúscula\\ - mr.p
    private static final Pattern HAS_UPPERCASE = Pattern.compile(".*[A-Z].*");

    //Regex para verificar se tem ao menos uma letra minúscula\\ - mr.p
    private static final Pattern HAS_LOWERCASE = Pattern.compile(".*[a-z].*");

    //Regex para verificar se tem ao menos 8 caracteres e no máximo 30\\ - mr.p
    private static final Pattern HAS_VALID_LENGTH = Pattern.compile("^.{8,30}$");

    public static boolean isValid(String password) throws InvalidPasswordException {
        if (password == null || password.isEmpty()) {
            throw new InvalidPasswordException("A Senha é nula ou vazia!");
        }
        if (!HAS_NUMBER.matcher(password).matches()) {
            throw new InvalidPasswordException("A senha deve conter ao menos um número!");
        }
        if (!HAS_LOWERCASE.matcher(password).matches()) {
            throw new InvalidPasswordException("A senha deve conter ao menos um caractere minúsculo!");
        }
        if (!HAS_UPPERCASE.matcher(password).matches()) {
            throw new InvalidPasswordException("A senha deve conter ao menos um caractere maiúsculo!");
        }
        if (!HAS_VALID_LENGTH.matcher(password).matches()) {
            throw new InvalidPasswordException("A senha deve conter ao menos 8 caracteres e no maximo 30");
        }
        if (!HAS_SPECIAL_CHAR.matcher(password).matches()) {
            throw new InvalidPasswordException("A senha deve conter ao menos um caractere especial");
        }
        return true;
    }
}
