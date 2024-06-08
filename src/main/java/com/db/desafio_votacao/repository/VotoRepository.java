package com.db.desafio_votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.desafio_votacao.enuns.VotoEnum;
import com.db.desafio_votacao.model.Voto;

public interface VotoRepository extends JpaRepository<Voto, Long> {
	boolean existsByPautaIdAndAssociadoId(Long pautaId, String associadoId);
    long countByPautaIdAndVoto(Long pautaId, VotoEnum voto);
}
