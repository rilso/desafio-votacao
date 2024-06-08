package com.db.desafio_votacao.usecase.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.desafio_votacao.exception.LimiteSessaoPautaException;
import com.db.desafio_votacao.exception.PautaInexistenteException;
import com.db.desafio_votacao.model.Sessao;
import com.db.desafio_votacao.repository.PautaRepository;
import com.db.desafio_votacao.repository.SessaoRepository;
import com.db.desafio_votacao.usecase.SessaoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SessaoServiceImpl implements SessaoService {
	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Autowired
	private PautaRepository pautaRepository;
	
	@Transactional
	public Sessao abrirSessao(Long pautaId, Optional<Long> duracaoEmSegundos) {
		log.info("(SessaoServiceImpl) Chamada para abrir sessao, pauta: "+pautaId);
		
		if (!pautaRepository.findById(pautaId).isPresent()) {
			throw new PautaInexistenteException();
		}
		if (sessaoRepository.findByPautaId(pautaId).isPresent()) {
			throw new LimiteSessaoPautaException();
		}
		
		LocalDateTime inicio = LocalDateTime.now();
		LocalDateTime fim = inicio.plusSeconds(duracaoEmSegundos.orElse(60L));
		Sessao sessao = new Sessao();
		sessao.setPautaId(pautaId);
		sessao.setInicio(inicio);
		sessao.setFim(fim);
		return sessaoRepository.save(sessao);
	}
}
