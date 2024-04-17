package br.com.votacao.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import br.com.votacao.exception.SessionNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.repository.SessionRepository;
import br.com.votacao.service.impl.SessionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
 class SessionServiceImplTest {

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Test
    void findById_ExistingId_ReturnsSession() {
        // Arrange
        Long sessionId = 1L;
        LocalDateTime dataInicio = LocalDateTime.now();
        Session expectedSession = Session.builder()
                .id(sessionId)
                .dataInicio(dataInicio)
                .minutosValidade(60L)
                .pauta(new Pauta())
                .build();
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(expectedSession));

        // Act
        Session result = sessionService.findById(sessionId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedSession, result);
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        // Arrange
        Long sessionId = 1L;
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(SessionNotFoundException.class, () -> sessionService.findById(sessionId));
    }

    @Test
    void abrirSessaoDeVotacao_DefaultDuration_ReturnsSession() {
        // Arrange
        Long pautaId = 1L;
        LocalDateTime currentDateTime = LocalDateTime.now();
        Long defaultDuration = 60L;
        Session expectedSession = Session.builder()
                .dataInicio(currentDateTime)
                .minutosValidade(defaultDuration)
                .pauta(new Pauta())
                .build();
        when(sessionRepository.save(any())).thenReturn(expectedSession);

        // Act
        Session result = sessionService.abrirSessaoDeVotacao(pautaId, null);

        // Assert
        assertNotNull(result);
        assertEquals(currentDateTime, result.getDataInicio());
        assertEquals(defaultDuration, result.getMinutosValidade());
        assertNotNull(result.getPauta());
    }

    // Teste adicional para verificar se os valores passados ​​são salvos corretamente
    @Test
    void abrirSessaoDeVotacao_CustomDuration_ReturnsSession() {
        // Arrange
        Long pautaId = 1L;
        LocalDateTime currentDateTime = LocalDateTime.now();
        Long customDuration = 120L;
        Session expectedSession = Session.builder()
                .dataInicio(currentDateTime)
                .minutosValidade(customDuration)
                .pauta(new Pauta())
                .build();
        when(sessionRepository.save(any())).thenReturn(expectedSession);

        // Act
        Session result = sessionService.abrirSessaoDeVotacao(pautaId, customDuration);

        // Assert
        assertNotNull(result);
        assertEquals(currentDateTime, result.getDataInicio());
        assertEquals(customDuration, result.getMinutosValidade());
        assertNotNull(result.getPauta());
    }
}
