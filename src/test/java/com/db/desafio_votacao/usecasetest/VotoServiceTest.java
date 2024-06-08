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
import com.db.desafio_votacao.enuns.VotoEnum;
import com.db.desafio_votacao.exception.LimiteVotoAssociadoException;
import com.db.desafio_votacao.exception.PautaSemSessaoAtivaException;
import com.db.desafio_votacao.model.Sessao;
import com.db.desafio_votacao.model.Voto;
import com.db.desafio_votacao.repository.SessaoRepository;
import com.db.desafio_votacao.repository.VotoRepository;
import com.db.desafio_votacao.usecase.impl.VotoServiceImpl;

@ExtendWith(MockitoExtension.class)
public class VotoServiceTest {

	@Mock
    private VotoRepository votoRepository;

    @Mock
    private SessaoRepository sessaoRepository;

    @InjectMocks
    private VotoServiceImpl votoService;
    
    @Test
    void testRegistrarVoto() {
        Long pautaId = 1L;
        String associadoId = "123";
        VotoEnum votoEnum = VotoEnum.SIM;
        Sessao sessao = new Sessao();
        sessao.setPautaId(pautaId);
        sessao.setInicio(LocalDateTime.now().minusMinutes(1));
        sessao.setFim(LocalDateTime.now().plusMinutes(1));
        
        Voto voto = new Voto();
        voto.setPautaId(pautaId);
        voto.setAssociadoId(associadoId);
        voto.setVoto(votoEnum);

        when(sessaoRepository.findByPautaId(pautaId)).thenReturn(Optional.of(sessao));
        when(votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)).thenReturn(false);
        when(votoRepository.save(voto)).thenReturn(voto);

        Voto result = votoService.registrarVoto(pautaId, associadoId, votoEnum);

        assertNotNull(result);
        assertEquals(pautaId, result.getPautaId());
        assertEquals(associadoId, result.getAssociadoId());
        assertEquals(votoEnum, result.getVoto());
    }

    @Test
    void testRegistrarVotoSessaoNaoEncontrada() {
        Long pautaId = 1L;
        String associadoId = "123";
        VotoEnum votoEnum = VotoEnum.SIM;

        when(sessaoRepository.findByPautaId(pautaId)).thenReturn(Optional.empty());

        assertThrows(PautaSemSessaoAtivaException.class, () -> votoService.registrarVoto(pautaId, associadoId, votoEnum));
    }

    @Test
    void testRegistrarVotoSessaoExpirada() {
        Long pautaId = 1L;
        String associadoId = "123";
        VotoEnum votoEnum = VotoEnum.SIM;
        Sessao sessao = new Sessao();
        sessao.setPautaId(pautaId);
        sessao.setInicio(LocalDateTime.now().minusMinutes(2));
        sessao.setFim(LocalDateTime.now().minusMinutes(1));

        when(sessaoRepository.findByPautaId(pautaId)).thenReturn(Optional.of(sessao));

        assertThrows(PautaSemSessaoAtivaException.class, () -> votoService.registrarVoto(pautaId, associadoId, votoEnum));
    }

    @Test
    void testRegistrarVotoAssociadoJaVotou() {
        Long pautaId = 1L;
        String associadoId = "123";
        VotoEnum votoEnum = VotoEnum.SIM;
        Sessao sessao = new Sessao();
        sessao.setPautaId(pautaId);
        sessao.setInicio(LocalDateTime.now().minusMinutes(1));
        sessao.setFim(LocalDateTime.now().plusMinutes(1));

        when(sessaoRepository.findByPautaId(pautaId)).thenReturn(Optional.of(sessao));
        when(votoRepository.existsByPautaIdAndAssociadoId(pautaId, associadoId)).thenReturn(true);

        assertThrows(LimiteVotoAssociadoException.class, () -> votoService.registrarVoto(pautaId, associadoId, votoEnum));
    }

    @Test
    void testContarVotosSim() {
        Long pautaId = 1L;
        long expectedCount = 10;

        when(votoRepository.countByPautaIdAndVoto(pautaId, VotoEnum.SIM)).thenReturn(expectedCount);

        long result = votoService.contarVotosSim(pautaId);

        assertEquals(expectedCount, result);
    }

    @Test
    void testContarVotosNao() {
        Long pautaId = 1L;
        long expectedCount = 5;

        when(votoRepository.countByPautaIdAndVoto(pautaId, VotoEnum.NAO)).thenReturn(expectedCount);

        long result = votoService.contarVotosNao(pautaId);

        assertEquals(expectedCount, result);
    }
}
