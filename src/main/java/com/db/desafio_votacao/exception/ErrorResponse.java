package com.db.desafio_votacao.exception;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse {
	private String menssagem;
	private List<String> detalhes;

    public ErrorResponse(String menssagem, List<String> detalhes) {
        this.menssagem = menssagem;
        this.detalhes = detalhes;
    }
}
