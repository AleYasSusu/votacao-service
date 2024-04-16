package br.com.votacao.service;


import br.com.votacao.dto.VotacaoResultadoDTO;
import br.com.votacao.dto.VoteRequestDTO;

public interface VoteService {

    void receiveVote(VoteRequestDTO voteRequest);

    String getResultadoVotacaoPauta(Long sessionId);
}
