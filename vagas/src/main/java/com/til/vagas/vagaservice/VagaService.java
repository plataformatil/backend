package com.til.vagas.vagaservice;


import com.til.vagas.domain.entities.Vaga;
import com.til.vagas.domain.entities.exceptions.InvalidVagaException;
import com.til.vagas.domain.validations.VagaValidation;
import com.til.vagas.infraestructure.repositories.IVagaRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    private final IVagaRepositorie iVagaRepositorie;

    @Autowired
    public VagaService(IVagaRepositorie iVagaRepositorie) {
        this.iVagaRepositorie = iVagaRepositorie;
    }

    public Vaga createVaga(Vaga vaga) throws InvalidVagaException {
        VagaValidation.isValid(vaga);
        return iVagaRepositorie.save(vaga);
    }


    public Vaga updateVaga(Integer id, Vaga updatedVaga) {
        Optional<Vaga> existingVagaOptional = iVagaRepositorie.findById(id);

        if (existingVagaOptional.isPresent()) {
            Vaga existingVaga = existingVagaOptional.get();
            existingVaga.setTitle(updatedVaga.getTitle());
            existingVaga.setDescription(updatedVaga.getDescription());
            existingVaga.setSalary(updatedVaga.getSalary());
            existingVaga.setLocation(updatedVaga.getLocation());

            return iVagaRepositorie.save(existingVaga);
        } else {
            throw new IllegalArgumentException("Vaga com ID " + id + " não encontrada.");
        }
    }


    public void deleteVaga(Integer id) {
        if (iVagaRepositorie.existsById(id)) {
            iVagaRepositorie.deleteById(id);
        } else {
            throw new IllegalArgumentException("Vaga com ID " + id + " não encontrada.");
        }
    }


    public List<Vaga> getVagas() {
        return iVagaRepositorie.findAll();
    }


    public Optional<Vaga> getVagaById(Integer id) {
        return iVagaRepositorie.findById(id);
    }

    public List<Vaga> getVagasAbertasNaSemana() {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(java.time.DayOfWeek.MONDAY));
        LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(java.time.DayOfWeek.SUNDAY));

        return iVagaRepositorie.findVagasAbertasNaSemana(startOfWeek, endOfWeek);
    }

    public int getNumeroDeVagasAbertasNaSemana() {
        return getVagasAbertasNaSemana().size();
    }
}


