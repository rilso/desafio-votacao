package com.db.desafio_votacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.db.desafio_votacao.model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long> {

}
