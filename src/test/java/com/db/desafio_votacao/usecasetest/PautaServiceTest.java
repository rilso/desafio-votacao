package com.db.desafio_votacao.usecasetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.db.desafio_votacao.model.Pauta;
import com.db.desafio_votacao.repository.PautaRepository;
import com.db.desafio_votacao.usecase.impl.PautaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PautaServiceTest {

	@Mock
    private PautaRepository pautaRepository;
	
	@InjectMocks
    private PautaServiceImpl pautaService;
	
	@Test
    void testCriarPauta() {
        Pauta pauta = new Pauta();
        pauta.setDescricao("Aumento da equipe");

        when(pautaRepository.save(pauta)).thenReturn(pauta);

        Pauta result = pautaService.criarPauta(pauta);

        assertNotNull(result);
        assertEquals("Aumento da equipe", result.getDescricao());
    }
}
