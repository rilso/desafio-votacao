package com.db.desafio_votacao.usecase.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.desafio_votacao.model.Pauta;
import com.db.desafio_votacao.repository.PautaRepository;
import com.db.desafio_votacao.usecase.PautaService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PautaServiceImpl implements PautaService {
	@Autowired
	private PautaRepository pautaRepository;
	
	@Transactional
	public Pauta criarPauta(Pauta pauta) {
		log.info("(PautaServiceImpl) Criando pauta "+pauta.getDescricao());
		
		return pautaRepository.save(pauta);
	}
}
