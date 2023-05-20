package br.com.votacao.service;

import br.com.votacao.domain.VotingDto;
import br.com.votacao.model.Vote;

import java.util.List;

public interface VotingService {
    Vote save(Vote voto);

    void delete(Vote voto);

    List<Vote> findVotosByPautaId(Long id);

    VotingDto getResultVotacao(Long id);

    VotingDto buildVotingPauta(Long id);
}
