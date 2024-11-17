package com.til.vagas.infraestructure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.til.vagas.domain.entities.Vaga;

@Repository
public interface IVagaRepositorie extends JpaRepository<Vaga, Long> {

    Optional<Vaga> findById(Long id);
    Vaga save(Vaga vaga);
    
}