package com.til.users.domain.entities;

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
    public UUID id;
    public String name;
    public String email;
    public String cpf;
    public String password;
    @Enumerated(EnumType.STRING)
    public Roles roles;
    @ManyToOne
    @JoinColumn(name = "address_id")
    public Address address;
}
