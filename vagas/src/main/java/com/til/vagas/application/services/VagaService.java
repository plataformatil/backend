package com.til.vagas.application.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.til.vagas.domain.entities.Vaga;
import com.til.vagas.infraestructure.repositories.IVagaRepository;

@Service
public class VagaService {

	private final IVagaRepository vagaRepository;

	public VagaService(IVagaRepository vagaRepository) {
		this.vagaRepository = vagaRepository;
	}

	public Page<Vaga> buscarVagas(String cargo, String localizacao, int pagina, int tamanho) {
		Pageable pageable = PageRequest.of(pagina, tamanho);
		return vagaRepository.findByFilters(cargo, localizacao, pageable);
	}

	public Vaga atualizarVaga(Long id, Vaga novaVaga) {
		Optional<Vaga> vagaExistenteOptional = vagaRepository.findById(id);

		if (vagaExistenteOptional.isEmpty()) {
			throw new IllegalArgumentException("Vaga não encontrada para o ID: " + id);
		}

		Vaga vagaExistente = vagaExistenteOptional.get();

		vagaExistente.setTitulo(novaVaga.getTitulo());
		vagaExistente.setSalario(novaVaga.getSalario());
		vagaExistente.setRua(novaVaga.getRua());
		vagaExistente.setCidade(novaVaga.getCidade());
		vagaExistente.setEstado(novaVaga.getEstado());
		vagaExistente.setPais(novaVaga.getPais());
		vagaExistente.setCep(novaVaga.getCep());
		vagaExistente.setModelo(novaVaga.getModelo());
		vagaExistente.setEmpresa(novaVaga.getEmpresa());
		vagaExistente.setNivel(novaVaga.getNivel());
		return vagaRepository.save(vagaExistente);
	}
}