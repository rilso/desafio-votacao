package com.db.desafio_votacao.exception;

public class PautaSemSessaoAtivaException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Não há uma sessão ativa para esta pauta.";
	}
}
