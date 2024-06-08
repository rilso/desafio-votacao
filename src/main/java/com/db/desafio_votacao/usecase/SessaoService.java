package com.db.desafio_votacao.usecase;

import java.util.Optional;

import com.db.desafio_votacao.model.Sessao;

public interface SessaoService {

	public Sessao abrirSessao(Long pautaId, Optional<Long> duracaoEmSegundos);
}
