package com.db.desafio_votacao.usecase;

import com.db.desafio_votacao.enuns.VotoEnum;
import com.db.desafio_votacao.model.Voto;

public interface VotoService {

	public Voto registrarVoto(Long pautaId, String associadoId, VotoEnum voto);
	public long contarVotosSim(Long pautaId);
	public long contarVotosNao(Long pautaId);
}
