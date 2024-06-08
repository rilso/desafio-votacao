package com.db.desafio_votacao.exception;

public class LimiteSessaoPautaException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Só é possível criar uma sessão por pauta.";
	}
}
