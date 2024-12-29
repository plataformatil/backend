package com.til.vagas.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vagas")
@NoArgsConstructor
@AllArgsConstructor
@Data 	
public class Vaga {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;
	private Double salario;
	private String localizacao; // Combinar cidade, estado, ou CEP
	private String rua;
	private String cidade;
	private String estado;
	private String pais;
	private String cep;
	private String modelo; // Presencial, Remoto ou Híbrido
	private String empresa;
	private String nivel; // Estágio, aprendiz, etc.

}
