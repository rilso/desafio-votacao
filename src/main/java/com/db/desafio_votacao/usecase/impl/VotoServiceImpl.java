package com.db.desafio_votacao.usecase.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.db.desafio_votacao.enuns.VotoEnum;
import com.db.desafio_votacao.exception.LimiteVotoAssociadoException;
import com.db.desafio_votacao.exception.PautaSemSessaoAtivaException;
import com.db.desafio_votacao.model.Sessao;
import com.db.desafio_votacao.model.Voto;
import com.db.desafio_votacao.repository.SessaoRepository;
import com.db.desafio_votacao.repository.VotoRepository;
import com.db.desafio_votacao.usecase.VotoService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VotoServiceImpl implements VotoService {
	@Autowired
	private VotoRepository votoRepository;
	
	@Autowired
	private SessaoRepository sessaoRepository;
	
	@Transactional
	public Voto registrarVoto(Long pautaId, String associadoId, VotoEnum voto) {
		log.info("(VotoServiceImpl) Chamada para registrar voto, pauta: "+pautaId+", associado: "+associadoId);
		
		Sessao sessao = sessaoRepository.findByPautaId(pautaId).orElseThrow(() -> new PautaSemSessaoAtivaException());
		if (sessao.getFim().isBefore(LocalDateTime.now())) {
			throw new PautaSemSessaoAtivaException();
		}
		if (votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)) {
			throw new LimiteVotoAssociadoException();
		}
		
		Voto novoVoto = new Voto();
		novoVoto.setPautaId(pautaId);
		novoVoto.setAssociadoId(associadoId);
		novoVoto.setVoto(voto);
		return votoRepository.save(novoVoto);
	}
	
	public long contarVotosSim(Long pautaId) {
		log.info("(VotoServiceImpl) Contabilizando os votos SIM, pauta: "+pautaId);
		
		return votoRepository.countByPautaIdAndVoto(pautaId, VotoEnum.SIM);
	}
	
	public long contarVotosNao(Long pautaId) {
		log.info("(VotoServiceImpl) Contabilizando os votos NAO, pauta: "+pautaId);
		
		return votoRepository.countByPautaIdAndVoto(pautaId, VotoEnum.NAO);
	}
}
