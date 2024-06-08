package com.db.desafio_votacao.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema
public class SessaoDTO {
	@Hidden
	private Long id;
	
	@Hidden
	private Long pautaId;
	
	@Hidden
	private String inicio;
	
	@Hidden
	private String fim;
}
