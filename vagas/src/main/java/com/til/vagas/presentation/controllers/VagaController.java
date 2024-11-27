package com.til.vagas.presentation.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@GetMapping
	public Page<Vaga> buscarVagas(@RequestParam(required = false) String cargo,
			@RequestParam(required = false) String localizacao, @RequestParam(defaultValue = "0") int pagina,
			@RequestParam(defaultValue = "6") int tamanho) {
		return vagaService.buscarVagas(cargo, localizacao, pagina, tamanho);
	}

	@PutMapping("{id}")
	public ResponseEntity<Vaga> atualizarVaga(@PathVariable Long id, @RequestBody Vaga novaVaga) {
		Vaga vagaAtualizada = vagaService.atualizarVaga(id, novaVaga);
		return ResponseEntity.ok(vagaAtualizada);
	}
}