package com.db.desafio_votacao.dto;

import com.db.desafio_votacao.enuns.VotoEnum;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema
public class VotoDTO {
	@Hidden
	private Long id;
	@Hidden
	private Long pautaId;
	
	@Schema(description = "Código do associado", example = "123")
	@NotBlank(message = "ID do associado não pode estar em branco.")
	private String associadoId;
	
	@Schema(description = "Voto", example = "NAO")
	@NotNull(message = "Voto não pode estar vazio.")
	private VotoEnum voto;
}
