package com.til.users.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID id;
    public String street;
    public String city;
    public String state;
    public String cep;
    public String country;
}
