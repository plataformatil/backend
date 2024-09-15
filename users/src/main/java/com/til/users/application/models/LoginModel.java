package com.til.users.application.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginModel {
    private String userNameOrEmail;
    private String password;
}
