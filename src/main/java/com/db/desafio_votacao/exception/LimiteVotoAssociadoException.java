package com.db.desafio_votacao.exception;

public class LimiteVotoAssociadoException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Associado já votou nesta pauta.";
	}
}
