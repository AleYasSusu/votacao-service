package br.com.votacao.controller;

import br.com.votacao.controller.impl.SessionControllerImpl;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.service.SessionService;
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

@RunWith(MockitoJUnitRunner.class)
public class SessionControllerImplTest {

    @Mock
    private SessionService sessionService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ObjectMapper objectMapper = new ObjectMapper();
        SessionControllerImpl sessionController = new SessionControllerImpl(sessionService);
        MockMvc mockMvc = MockMvcBuilders
                .standaloneSetup(sessionController)
                .build();
    }

    @Test
    public void findAll_ReturnsSessions() {
        // Arrange
        List<Session> sessions = Arrays.asList(new Session(), new Session(), new Session());
        when(sessionService.findAll()).thenReturn(sessions);

        // Act
        List<Session> result = sessionService.findAll();

        // Assert
        assertEquals(sessions, result);
        verify(sessionService, times(1)).findAll();
    }

    @Test
    public void createSession_ReturnsCreatedSession() {
        // Arrange
        Pauta pauta = new Pauta();
        Long minutosValidade = 10L;
        Session createdSession = new Session();
        when(sessionService.createSession(pauta, minutosValidade)).thenReturn(createdSession);

        // Act
        Session result = sessionService.createSession(pauta, minutosValidade);

        // Assert
        assertEquals(createdSession, result);
        verify(sessionService, times(1)).createSession(pauta, minutosValidade);
    }

    @Test
    public void findSessaoById_ReturnsSession() {
        // Arrange
        Long sessionId = 1L;
        Session session = new Session();
        when(sessionService.findById(sessionId)).thenReturn(session);

        // Act
        Session result = sessionService.findById(sessionId);

        // Assert
        assertEquals(session, result);
        verify(sessionService, times(1)).findById(sessionId);
    }

    @Test
    public void findSessaoByIdAndPautaId_ReturnsSession() {
        // Arrange
        Long pautaId = 1L;
        Long sessionId = 1L;
        Session session = new Session();
        when(sessionService.findByIdAndPautaId(pautaId, sessionId)).thenReturn(session);

        // Act
        Session result = sessionService.findByIdAndPautaId(pautaId, sessionId);

        // Assert
        assertEquals(session, result);
        verify(sessionService, times(1)).findByIdAndPautaId(pautaId, sessionId);
    }

    @Test
    public void delete_ValidId_ReturnsOk() {
        // Arrange
        Long id = 1L;
        doNothing().when(sessionService).delete(id);

        // Act
        sessionService.delete(id);

        // Assert
        verify(sessionService, times(1)).delete(id);
    }

}
