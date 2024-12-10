package com.til.vagas.presentation.controllers;


import com.til.vagas.domain.entities.Vaga;
import com.til.vagas.domain.entities.exceptions.InvalidVagaException;
import com.til.vagas.domain.validations.VagaValidation;
import com.til.vagas.vagaservice.VagaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createVaga(@RequestBody Vaga vaga) throws InvalidVagaException {
        VagaValidation.isValid(vaga);
        Vaga createdVaga = vagaService.createVaga(vaga);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Vaga adicionada com sucesso.");
        response.put("vaga", createdVaga);

        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateVaga(@PathVariable Integer id, @RequestBody Vaga vaga) {
        try {
            Vaga updatedVaga = vagaService.updateVaga(id, vaga);
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Vaga com ID " + id + " foi atualizada com sucesso.");
            response.put("vaga", updatedVaga);

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Vaga com ID " + id + " não encontrada.");
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaga(@PathVariable Integer id) {
        try {
            vagaService.deleteVaga(id);
            return ResponseEntity.ok("Vaga com ID " + id + " foi deletada com sucesso.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga com ID " + id + " não encontrada.");
        }
    }


    @GetMapping
    public ResponseEntity<List<Vaga>> Vagas() {
        List<Vaga> vagas = vagaService.getVagas();
        return ResponseEntity.ok(vagas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Vaga> getVagaById(@PathVariable Integer id) {
        return vagaService.getVagaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/processos-abertos-semana")
    public ResponseEntity<Map<String, Object>> getProcessosAbertosNaSemana() {
        int numeroDeVagas = vagaService.getNumeroDeVagasAbertasNaSemana();
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Número de vagas abertas na semana.");
        response.put("vagasAbertas", numeroDeVagas);

        return ResponseEntity.ok(response);
    }
}


