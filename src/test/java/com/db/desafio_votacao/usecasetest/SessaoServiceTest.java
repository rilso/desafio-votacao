package com.db.desafio_votacao.usecasetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.db.desafio_votacao.exception.LimiteSessaoPautaException;
import com.db.desafio_votacao.exception.PautaInexistenteException;
import com.db.desafio_votacao.model.Pauta;
import com.db.desafio_votacao.model.Sessao;
import com.db.desafio_votacao.repository.PautaRepository;
import com.db.desafio_votacao.repository.SessaoRepository;
import com.db.desafio_votacao.usecase.impl.SessaoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class SessaoServiceTest {

	@Mock
    private SessaoRepository sessaoRepository;

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private SessaoServiceImpl sessaoService;
    
    @Test
    void testAbrirSessaoPautaNaoEncontrada() {
        Long pautaId = 1L;

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.empty());

        assertThrows(PautaInexistenteException.class, () -> sessaoService.abrirSessao(pautaId, Optional.empty()));
    }
    
    @Test
    void testAbrirSessaoSessaoJaExisteParaPauta() {
        Long pautaId = 1L;

        when(pautaRepository.findById(pautaId)).thenReturn(Optional.of(new Pauta()));
        when(sessaoRepository.findByPautaId(pautaId)).thenReturn(Optional.of(new Sessao()));

        assertThrows(LimiteSessaoPautaException.class, () -> sessaoService.abrirSessao(pautaId, Optional.empty()));
    }
}
