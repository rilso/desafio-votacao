package com.db.desafio_votacao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.desafio_votacao.model.Sessao;

public interface SessaoRepository extends JpaRepository<Sessao, Long> {
	Optional<Sessao> findByPautaId(Long pautaId);
	
}
