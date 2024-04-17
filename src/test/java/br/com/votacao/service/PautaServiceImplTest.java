package br.com.votacao.service;

import br.com.votacao.mock.PautaMock;
import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.service.impl.PautaServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceImplTest {

    @Mock
    private PautaRepository pautaRepository;

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Test
    public void testFindAllPautas() {
        // Given
        List<Pauta> pautas = new ArrayList<>();
        pautas.add(PautaMock.buildPauta());
        pautas.add(PautaMock.buildPauta());
        when(pautaRepository.findAll()).thenReturn(pautas);

        // When
        List<Pauta> result = pautaService.findAllPautas();

        // Then
        assertThat(result).hasSize(2);
        verify(pautaRepository, times(1)).findAll();
    }

    @Test
    public void testCreateNewPauta() {
        // Given
        Pauta pauta = new Pauta();
        when(pautaRepository.save(pauta)).thenReturn(pauta);

        // When
        Pauta result = pautaService.createNewPauta(pauta);

        // Then
        assertThat(result).isNotNull();
        verify(pautaRepository, times(1)).save(pauta);
    }

    @Test
    public void testFindPautaById() {
        // Given
        Long id = 1L;
        Pauta pauta = new Pauta();
        when(pautaRepository.findById(id)).thenReturn(Optional.of(pauta));

        // When
        Pauta result = pautaService.findById(id);

        // Then
        assertThat(result).isNotNull();
        verify(pautaRepository, times(1)).findById(id);
    }
}
