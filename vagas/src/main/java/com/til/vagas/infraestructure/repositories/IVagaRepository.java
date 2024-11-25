package com.til.vagas.infraestructure.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.til.vagas.domain.entities.Vaga;

@Repository
public interface IVagaRepository extends JpaRepository<Vaga, Long> {

	@Query("SELECT v FROM Vaga v WHERE "
			+ "(:cargo IS NULL OR LOWER(v.titulo) LIKE LOWER(CONCAT('%', :cargo, '%'))) AND "
			+ "(:localizacao IS NULL OR LOWER(v.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%')))")
	Page<Vaga> findByFilters(@Param("cargo") String cargo, @Param("localizacao") String localizacao, Pageable pageable);

	Optional<Vaga> findById(Long id);

	Vaga save(Vaga vaga);
}