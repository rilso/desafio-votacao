package com.db.desafio_votacao.model;

import com.db.desafio_votacao.enuns.VotoEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Voto {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pautaId;
    private String associadoId;
    
    @Enumerated(EnumType.STRING)
    private VotoEnum voto;
}
