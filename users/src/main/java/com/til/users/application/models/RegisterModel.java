package com.til.users.application.models;

import com.til.users.domain.entities.Address;
import com.til.users.domain.enums.Roles;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterModel {
    private String userName;
    private String email;
    private String cpf;
    private String cnpj;
    private Roles roles;
    private Address address;
    private String password;
    private String confirmPassword;
}
