package com.til.vagas.infraestructure.repositories;

import com.til.vagas.domain.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface IVagaRepositorie extends JpaRepository<Vaga, Integer> {
    boolean existsByTitle(String title);

    @Query("SELECT v FROM Vaga v WHERE v.selectionProcessOpen = true AND v.openingDate >= :startOfWeek AND v.openingDate <= :endOfWeek")
    List<Vaga> findVagasAbertasNaSemana(LocalDate startOfWeek, LocalDate endOfWeek);

    Optional<Vaga> findById(Integer id);
}

