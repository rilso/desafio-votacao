package com.db.desafio_votacao.mapper;

import org.mapstruct.Mapper;

import com.db.desafio_votacao.dto.SessaoDTO;
import com.db.desafio_votacao.model.Sessao;

@Mapper(componentModel = "spring")
public interface SessaoMapper {
	SessaoDTO toDTO(Sessao sessao);
	Sessao toEntity(SessaoDTO sessaoDTO);
}
