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

import com.db.desafio_votacao.dto.VotoDTO;
import com.db.desafio_votacao.mapper.VotoMapper;
import com.db.desafio_votacao.model.Voto;
import com.db.desafio_votacao.usecase.SessaoService;
import com.db.desafio_votacao.usecase.VotoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pautas/{pautaId}/votos")
@Tag(name = "Voto", description = "Gerenciamento de votos")
public class VotoController {
	@Autowired
	private VotoService votoService;
	
	@Autowired
	private VotoMapper votoMapper;
	
	@PostMapping
	@Operation(summary = "Registrar voto", description = "Registrar um voto de um associado em uma pauta")
	public ResponseEntity<VotoDTO> registrarVoto(@PathVariable Long pautaId, @Valid @RequestBody VotoDTO votoDTO) {
		Voto voto = votoService.registrarVoto(pautaId, votoDTO.getAssociadoId(), votoDTO.getVoto());
		return new ResponseEntity<>(votoMapper.toDTO(voto), HttpStatus.OK);
	}
	
	@GetMapping("/resultado")
	@Operation(summary = "Obter resultado de votação", description = "Obter o resultado da votação de uma pauta")
	public ResponseEntity<String> obterResultado(@PathVariable Long pautaId) {
		long votosSim = votoService.contarVotosSim(pautaId);
		long votosNao = votoService.contarVotosNao(pautaId);
		return new ResponseEntity<>(String.format("Resultado: %d votos SIM, %d votos NÃO", votosSim, votosNao), HttpStatus.OK);
	}
	
}
