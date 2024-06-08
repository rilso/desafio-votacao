package com.db.desafio_votacao.mapper;

import org.mapstruct.Mapper;

import com.db.desafio_votacao.dto.PautaDTO;
import com.db.desafio_votacao.model.Pauta;

@Mapper(componentModel = "spring")
public interface PautaMapper {
	PautaDTO toDTO(Pauta pauta);
	Pauta toEntity(PautaDTO pautaDTO);
}
