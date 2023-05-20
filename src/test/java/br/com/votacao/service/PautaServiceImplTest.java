package br.com.votacao.service;

import br.com.votacao.model.Pauta;
import br.com.votacao.repository.PautaRepository;
import br.com.votacao.service.impl.PautaServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static br.com.votacao.builder.PautaBuilder.buildPautaDefault;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PautaServiceImplTest {

    @InjectMocks
    private PautaServiceImpl pautaService;

    @Mock
    private PautaRepository pautaRepository;

    @Test
    public void shouldSavePauta() {
        // Dados de entrada
        Pauta pauta = buildPautaDefault().build();

        // Mock do repositório
        Mockito.when(pautaRepository.save(pauta)).thenReturn(pauta);

        // Chamar o método a ser testado
        Pauta save = pautaService.createNewPauta(pauta);

        // Verificar se o método save do repositório foi chamado corretamente
        verify(pautaRepository, Mockito.times(1)).save(pauta);

        // Verificar o resultado esperado
        Assertions.assertEquals(pauta, save);
    }

    @Test
    public void shouldFindAllPauta() {
        //Data
        List<Pauta> pautaList = buildPautaDefault().buildList();

        when(pautaRepository.findAll()).thenReturn(pautaList);
        //Action
        List<Pauta> allPauta = pautaService.findAllPautas();

        //Result
        assertThat(allPauta).isEqualTo(pautaList);
    }

    @Test
    public void shouldFindPautaById() {
        //Data
        Pauta pauta = buildPautaDefault().build();
        when(pautaRepository.findById(10L)).thenReturn(of(pauta));
        //Action
        Pauta result = pautaService.findById(10L);
        //Result
        assertThat(result).isEqualTo(pauta);
    }

    @Test
    public void shouldDeleteStaff() {
        //Data
        Pauta staff = buildPautaDefault().build();

        when(pautaRepository.findById(10L)).thenReturn(of(staff));

        //Action
        pautaService.delete(10L);

        //Result
        verify(pautaRepository, times(1)).delete(staff);
    }

}
