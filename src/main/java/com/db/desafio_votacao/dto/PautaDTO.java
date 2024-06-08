package com.db.desafio_votacao.dto;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Detalhes da pauta")
public class PautaDTO {
	@Hidden
	private Long id;
	
	@Schema(description = "Descrição da pauta", example = "Aumento da equipe")
	@NotBlank(message = "Informe uma descrição para a pauta.")
	private String descricao;
}
