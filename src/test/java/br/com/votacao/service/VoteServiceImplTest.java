package br.com.votacao.service;

import br.com.votacao.exception.SessionTimeOutException;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.exception.VoteNotFoundException;
import br.com.votacao.mock.SessionMock;
import br.com.votacao.model.Session;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.impl.VoteServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import br.com.votacao.dto.VoteRequestDTO;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {

    @Mock
    private SessionService sessionService;

    @Mock
    private MockCpfService cpfServiceClient;

    @Mock
    private VoteRepository voteRepository;

    @InjectMocks
    private VoteServiceImpl voteService;

    @Test
    public void testReceiveVote_CpfEnabled() {
        VoteRequestDTO voteRequestDTO = new VoteRequestDTO();
        voteRequestDTO.setSessionId(1L);
        voteRequestDTO.setCpf("86130113064");
        voteRequestDTO.setEscolha("Sim");

        Session session = SessionMock.buildSession();
        session.setDataInicio(LocalDateTime.now().minusMinutes(30)); // Sessão válida
        session.setMinutosValidade(60L);
        when(sessionService.findById(1L)).thenReturn(session);
        when(cpfServiceClient.checkVotingAbility("86130113064")).thenReturn("ABLE_TO_VOTE");
        when(voteRepository.existsByCpfAndSessionId("86130113064", 1L)).thenReturn(false);

        assertDoesNotThrow(() -> voteService.receiveVote(voteRequestDTO));
        verify(voteRepository, times(1)).save(any());
    }

    @Test
    public void testReceiveVote_CpfDisabled() {
        VoteRequestDTO voteRequestDTO = new VoteRequestDTO();
        voteRequestDTO.setSessionId(1L);
        voteRequestDTO.setCpf("69750936019");
        voteRequestDTO.setEscolha("Sim");

        Session session = SessionMock.buildSession();
        session.setDataInicio(LocalDateTime.now().minusMinutes(30)); // Sessão válida
        session.setMinutosValidade(60L);
        when(sessionService.findById(1L)).thenReturn(session);
        when(cpfServiceClient.checkVotingAbility("69750936019")).thenReturn("UNABLE_TO_VOTE");

        assertThrows(UnableCpfException.class, () -> voteService.receiveVote(voteRequestDTO));
        verify(voteRepository, never()).save(any());
    }

    @Test
    public void testReceiveVote_SessionTimeoutException() {
        VoteRequestDTO voteRequestDTO = new VoteRequestDTO();
        voteRequestDTO.setSessionId(1L);
        Session session = SessionMock.buildSession();
        session.setDataInicio(LocalDateTime.now().plusMinutes(30));
        when(sessionService.findById(1L)).thenReturn(session);

        assertThrows(SessionTimeOutException.class, () -> voteService.receiveVote(voteRequestDTO));
        verify(voteRepository, never()).save(any());
    }


    @Test
    public void testGetResultadoVotacaoPauta_NoVotes() {
        when(voteRepository.findBySessionId(1L)).thenReturn(Collections.emptyList());

        assertThrows(VoteNotFoundException.class, () -> voteService.getResultadoVotacaoPauta(1L));
    }


}