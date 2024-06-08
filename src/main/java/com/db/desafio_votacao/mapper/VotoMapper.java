package com.db.desafio_votacao.mapper;

import org.mapstruct.Mapper;

import com.db.desafio_votacao.dto.VotoDTO;
import com.db.desafio_votacao.model.Voto;

@Mapper(componentModel = "spring")
public interface VotoMapper {
	VotoDTO toDTO(Voto voto);
	Voto toEntity(VotoDTO votoDTO);
}
