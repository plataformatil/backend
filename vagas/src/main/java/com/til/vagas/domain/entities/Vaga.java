package com.til.vagas.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;


@Data
@Entity
@Table(name = "vaga")
public class Vaga {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column(nullable = false)
    private String location;

    @Column(name = "selectionProcessOpen")
    private boolean selectionProcessOpen;

    @Column(name = "opening_date")
    private LocalDate openingDate;
}
