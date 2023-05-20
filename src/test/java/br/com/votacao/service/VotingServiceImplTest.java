package br.com.votacao.service;

import br.com.votacao.domain.VotingDto;
import br.com.votacao.exception.VoteNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.SessionRepository;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.impl.SessionServiceImpl;
import br.com.votacao.service.impl.VotingServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static br.com.votacao.builder.VoteBuilder.buildVoteDefault;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VotingServiceImplTest {
    @InjectMocks
    private VotingServiceImpl votingService;
    @Mock
    private VoteRepository voteRepository;


    @Test
    public void shouldSaveVOte() {
        //Data
        Vote vote = buildVoteDefault().build();

        when(voteRepository.save(vote))
                .thenReturn(vote);

        //Action
        Vote result = votingService.save(vote);

        //Result
        assertThat(result).isEqualTo(vote);
        verify(voteRepository, times(1)).save(vote);
    }


    @Test
    public void shouldThrowVoteNotFoundException() {
        //Action
        assertThatThrownBy(() -> votingService.findVotosByPautaId(1L))
                .isInstanceOf(VoteNotFoundException.class);

    }
    @Test
    public void shouldReturnAllVote() {
        //Data
        when(voteRepository.findAll())
                .thenReturn(singletonList(buildVoteDefault().build()));
        //Action
        List<Vote> allVotes = votingService.findAll();

        //Result
        assertThat(allVotes).isNotEmpty();
    }

}
