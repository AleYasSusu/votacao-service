package br.com.votacao.service;

import br.com.votacao.domain.VotacaoDto;
import br.com.votacao.model.Vote;

import java.util.List;

public interface VotingService {
    Vote save(Vote voto);

    void delete(Long id);

    List<Vote> findVotosByPautaId(Long id);

    VotacaoDto getResultVotacao(Long id);

    VotacaoDto buildVotacaoPauta(Long id);
}
