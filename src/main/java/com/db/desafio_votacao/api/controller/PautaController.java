package com.db.desafio_votacao.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.db.desafio_votacao.dto.PautaDTO;
import com.db.desafio_votacao.mapper.PautaMapper;
import com.db.desafio_votacao.model.Pauta;
import com.db.desafio_votacao.usecase.PautaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pautas")
@Tag(name = "Pauta", description = "Gerenciamento de Pautas")
public class PautaController {
	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private PautaMapper pautaMapper;
	
	@PostMapping
	@Operation(summary = "Criar pauta", description = "Criar uma pauta")
	public ResponseEntity<PautaDTO> criarPauta(@Valid @RequestBody PautaDTO pautaDTO) {
		Pauta pauta = pautaMapper.toEntity(pautaDTO);
		pauta = pautaService.criarPauta(pauta);
		return new ResponseEntity<>(pautaMapper.toDTO(pauta), HttpStatus.CREATED);
	}
}
