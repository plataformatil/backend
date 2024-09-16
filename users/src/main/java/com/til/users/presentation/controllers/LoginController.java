package com.til.users.presentation.controllers;

import com.til.users.application.models.LoginModel;
import com.til.users.application.services.LoginService;
import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidUserException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService service;

    @PostMapping("/authentication")
    public ResponseEntity<String> login(@NonNull @RequestBody LoginModel loginModel) {
        try {
            User user = service.login(loginModel);
            return ResponseEntity.status(HttpStatus.OK).body("Login bem sucedido! Bem vindo, " + user.getName());
        } catch (InvalidUserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro de login: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro interno do Servidor: " + ex.getMessage());
        }
    }
}
