package com.til.users.presentation.controllers;

import com.til.users.application.models.RegisterModel;
import com.til.users.application.services.RegisterService;
import com.til.users.domain.exceptions.InvalidUserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
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
    public ResponseEntity<String> createUser(@NonNull @RequestBody RegisterModel register, HttpServletRequest request) {
        try {
            if (register.getPassword().equals(register.getConfirmPassword())) {
                String ip = request.getRemoteAddr();
                var success = service.createUser(register, ip);
                if (success)
                    return ResponseEntity.status(HttpStatus.OK).body("Usuário adicionado com sucesso");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O campo Password e ConfirmPassword devem ser iguais.");
        } catch (InvalidUserException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possível adicionar usuário: " + ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + ex.getMessage());
        }
    }
}
