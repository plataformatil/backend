package com.til.vagas.application.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.til.vagas.domain.entities.Vaga;
import com.til.vagas.infraestructure.repositories.IVagaRepositorie;

@Service
public class VagaService {

	private final IVagaRepositorie vagaRepositorie;

	public VagaService(IVagaRepositorie vagaRepositorie) {
		this.vagaRepositorie = vagaRepositorie;
	}

	public Vaga atualizarVaga(Long id, Vaga novaVaga) {
		Optional<Vaga> vagaExistenteOptional = vagaRepositorie.findById(id);

		if (vagaExistenteOptional.isEmpty()) {
			throw new IllegalArgumentException("Vaga não encontrada para o ID: " + id);
		}

		Vaga vagaExistente = vagaExistenteOptional.get();

		vagaExistente.setTitulo(novaVaga.getTitulo());
		vagaExistente.setDescricao(novaVaga.getDescricao());
		vagaExistente.setLocalizacao(novaVaga.getLocalizacao());
		vagaExistente.setSalario(novaVaga.getSalario());
		vagaExistente.setRequisitos(novaVaga.getRequisitos());

		return vagaRepositorie.save(vagaExistente);
	}
}