package br.com.votacao.service;


import br.com.votacao.model.Vote;

import java.util.List;
import java.util.Optional;

public interface VoteService {

    Vote findById(Long id);

    List<Vote> findAll();

    Vote createNewVote(Long idPauta, Long idSessao, Vote voto);

    void cpfAbleToVote(Vote vote);

    List<Vote> findVotosByPautaId(Long id);

    void deleteByPautaId(Long id);

    Optional<Vote> findByCpf(String cpf);

    Optional<List<Vote>> findByPautaId(Long id);
}
