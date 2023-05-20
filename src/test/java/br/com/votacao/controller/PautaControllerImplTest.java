package br.com.votacao.controller;

import br.com.votacao.controller.impl.PautaControllerImpl;
import br.com.votacao.exception.PautaNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
public class PautaControllerImplTest {
    private MockMvc mockMvc;

    @Mock
    private PautaService pautaService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        PautaControllerImpl pautaController = new PautaControllerImpl(pautaService);
        mockMvc = MockMvcBuilders
                .standaloneSetup(pautaController)
                .build();
    }

    @Test
    public void delete_ValidId_ReturnsOk() throws Exception {
        // Arrange
        Long id = 1L;
        doNothing().when(pautaService).delete(eq(id));

        // Act & Assert
        mockMvc.perform(delete("/v1/pautas/{id}", id))
                .andExpect(status().isOk());

        verify(pautaService, times(1)).delete(eq(id));
    }

    @Test
    public void findById_ValidId_ReturnsPauta() {
        // Arrange
        Long id = 1L;
        Pauta pauta = new Pauta();
        pauta.setId(id);
        when(pautaService.findById(id)).thenReturn(pauta);

        // Act
        Pauta result = pautaService.findById(id);

        // Assert
        assertEquals(pauta, result);
        verify(pautaService, times(1)).findById(id);
    }

    @Test
    public void createNewPauta_ValidPauta_ReturnsPauta() {
        // Arrange
        Pauta pauta = new Pauta();
        pauta.setNome("Título da Pauta");
        when(pautaService.createNewPauta(pauta)).thenReturn(pauta);

        // Act
        Pauta result = pautaService.createNewPauta(pauta);

        // Assert
        assertEquals(pauta, result);
        verify(pautaService, times(1)).createNewPauta(pauta);
    }

    @Test
    public void findAll_ReturnsPautas() {
        // Arrange
        List<Pauta> pautas = Arrays.asList(new Pauta(), new Pauta(), new Pauta());
        when(pautaService.findAllPautas()).thenReturn(pautas);

        // Act
        List<Pauta> result = pautaService.findAllPautas();

        // Assert
        assertEquals(pautas, result);
        verify(pautaService, times(1)).findAllPautas();
    }

}