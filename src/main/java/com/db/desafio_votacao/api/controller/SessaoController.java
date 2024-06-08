package com.db.desafio_votacao.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio_votacao.dto.SessaoDTO;
import com.db.desafio_votacao.mapper.SessaoMapper;
import com.db.desafio_votacao.model.Sessao;
import com.db.desafio_votacao.usecase.SessaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/pautas/{pautaId}/sessoes")
@Tag(name = "Sessão", description = "Gerenciamento de sessões")
public class SessaoController {
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private SessaoMapper sessaoMapper;
	
	@PostMapping
	@Operation(summary = "Abrir sessão", description = "Abrir uma sessão de votação para uma pauta")
	public ResponseEntity<SessaoDTO> abrirSessao(@PathVariable Long pautaId, @RequestParam Optional<Long> duracaoEmSegundos) {
		Sessao sessao = sessaoService.abrirSessao(pautaId, duracaoEmSegundos);
		return new ResponseEntity<>(sessaoMapper.toDTO(sessao), HttpStatus.CREATED);
	}
}
