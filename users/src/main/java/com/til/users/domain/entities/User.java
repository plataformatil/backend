package com.til.users.domain.entities;

import com.til.users.application.models.RegisterModel;
import com.til.users.domain.enums.Roles;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String cpf;
    private String cnpj;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles roles;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    public User() {

    }

    public User(RegisterModel register) {
        this.name = register.getUserName();
        this.email = register.getEmail();
        this.cpf = register.getCpf();
        this.cnpj = register.getCnpj();
        this.password = register.getPassword();
        this.roles = register.getRoles();
        this.address = register.getAddress();
    }
}
