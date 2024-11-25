package com.til.vagas.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.til.vagas.application.services.VagaService;
import com.til.vagas.domain.entities.Vaga;

@RestController
@RequestMapping("/vagas")
public class VagaController {

	private final VagaService vagaService;

	public VagaController(VagaService vagaService) {
		this.vagaService = vagaService;
	}

	@PutMapping("{id}")
	public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga novaVaga) {
		Vaga vagaAtualizada = vagaService.atualizarVaga(id, novaVaga);
		return ResponseEntity.ok(vagaAtualizada);
	}
}