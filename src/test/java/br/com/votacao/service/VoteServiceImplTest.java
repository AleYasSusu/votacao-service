package br.com.votacao.service;

import br.com.votacao.builder.SessionBuilder;
import br.com.votacao.builder.VoteBuilder;
import br.com.votacao.domain.CpfValidationDto;
import br.com.votacao.domain.VotingDto;
import br.com.votacao.exception.InvalidCpfException;
import br.com.votacao.exception.SessionTimeOutException;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.exception.VoteAlreadyExistsException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.impl.VoteServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VoteServiceImplTest {
    @InjectMocks
    private VoteServiceImpl voteService;
    @Mock
    private VoteRepository votoRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private VotingService votacaoService;
    @Mock
    private SessionService sessaoService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test(expected = SessionTimeOutException.class)
    public void verifyVotoTest() {
        Session session = new Session();
        session.setDataInicio(LocalDateTime.now());
        session.setMinutosValidade(-1L);

        Vote voto = new Vote();
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        voto.setPauta(pauta);

        when(votacaoService.buildVotingPauta(anyLong())).thenReturn(VotingDto.builder().build());

        voteService.verifyVote(session, voto);
    }

    @Test(expected = VoteAlreadyExistsException.class)
    public void votoAlreadyExistsTest() {
        Vote voto = new Vote();
        voto.setCpf("1234");
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        voto.setPauta(pauta );
        when(votoRepository.findByCpfAndPautaId(anyString(), anyLong())).thenReturn(Optional.of(new Vote()));
        voteService.voteAlreadyExists(voto);
    }

    @Test
    public void votoAlreadyExistssTest() {
        Vote vote = new Vote();
        vote.setCpf("1234");
        Pauta pauta = new Pauta();
        pauta.setId(1L);
        vote.setPauta(pauta );

        when(votoRepository.findByCpfAndPautaId(anyString(), anyLong())).thenReturn(Optional.empty());
        voteService.voteAlreadyExists(vote);
    }
}