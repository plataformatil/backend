package com.til.users.presentation.controllers;

import com.til.users.application.services.RegisterService;
import com.til.users.domain.entities.User;
import com.til.users.domain.exceptions.InvalidUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class RegisterController {
    @Autowired
    private RegisterService service;

    @PostMapping("register")
    public ResponseEntity<String> createUser(@RequestBody User user) {
        try {
            if (user != null) {
                service.createUser(user);
                return ResponseEntity.status(HttpStatus.OK).body("Usuário adicionado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário passado é nulo");
        } catch (InvalidUserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível adicionar usuário: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
