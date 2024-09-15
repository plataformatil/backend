package com.til.users.application.services;

import com.til.users.application.models.LoginModel;
import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidUserException;
import com.til.users.infraestructure.repositories.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    private final IUserRepository repository;
    private final BCryptPasswordEncoder passEncoder;
    private final LoginAttemptService loginAttemptService;

    public LoginService(IUserRepository repository, BCryptPasswordEncoder passEncoder, LoginAttemptService loginAttemptService) {
        this.repository = repository;
        this.passEncoder = passEncoder;
        this.loginAttemptService = loginAttemptService;
    }

    public User login(LoginModel loginModel) throws InvalidUserException {
        String userNameOrEmail = loginModel.getUserNameOrEmail();
        if (loginAttemptService.isBlocked(userNameOrEmail)) {
            throw new InvalidUserException(
                    "Usuário temporariamente bloqueado devido a múltiplas " +
                            "tentativas de login falhadas");
        }
        User user = repository.findByUserOrEmail(userNameOrEmail)
                .orElseThrow(() -> new InvalidUserException("Usuário não encontrado"));

        if (!passEncoder.matches(loginModel.getPassword(), user.getPassword())) {
            loginAttemptService.loginFailed(userNameOrEmail);
            throw new InvalidUserException("Senha Inválida");
        }
        loginAttemptService.loginSucceeded(userNameOrEmail);
        return user;
    }
}
