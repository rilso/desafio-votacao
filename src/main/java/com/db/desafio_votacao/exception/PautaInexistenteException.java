package com.db.desafio_votacao.exception;

public class PautaInexistenteException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Pauta não encontrada.";
	}
}
