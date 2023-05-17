package br.com.votacao.service.impl;

import br.com.votacao.domain.VotacaoDto;
import br.com.votacao.exception.BusinessException;
import br.com.votacao.exception.VotacaoNotFoundException;
import br.com.votacao.exception.VotoNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import br.com.votacao.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotingServiceImpl implements VotingService {

    private final VoteService voteService;
    private final SessionService sessionService;

    private final VoteRepository voteRepository;

    @Autowired
    public VotingServiceImpl(VoteService voteService,
                             SessionService sessionService,
                             VoteRepository voteRepository) {
        this.voteService = voteService;
        this.sessionService = sessionService;
        this.voteRepository = voteRepository;
    }

    @Override
    public Vote save(final Vote voto) {
        verifyIfExists(voto);
        return voteRepository.save(voto);
    }

    private void verifyIfExists(final Vote voto) {
        Optional<Vote> votoByCpfAndPauta = voteService.findByCpf(voto.getCpf());

        if (votoByCpfAndPauta.isPresent() && (voto.isNew() || isNotUnique(voto, votoByCpfAndPauta.get()))) {
            throw new BusinessException(null, null);
        }
    }

    private boolean isNotUnique(Vote vote, Vote votoByCpfAndPauta) {
        return vote.alreadyExist() && !votoByCpfAndPauta.equals(vote);
    }

    @Override
    public void delete(Long id) {
        voteService.delete(id);
    }

    @Override
    public List<Vote> findVotosByPautaId(Long id) {
        Optional<List<Vote>> findByPautaId = voteService.findByPautaId(id);

        if (!findByPautaId.isPresent()) {
            throw new VotoNotFoundException();
        }

        return findByPautaId.get();
    }

    @Override
    public VotacaoDto getResultVotacao(Long id) {
        VotacaoDto votacaoPauta = buildVotacaoPauta(id);
        return votacaoPauta;
    }

    @Override
    public VotacaoDto buildVotacaoPauta(Long id) {
        List<Vote> votosByPauta = voteRepository.findByPautaId(id)
                .orElseThrow(VotacaoNotFoundException::new);

        if (votosByPauta.isEmpty()) {
            throw new VotacaoNotFoundException();
        }

        Pauta pauta = votosByPauta.get(0).getPauta();

        Long totalSessoes = sessionService.countSessoesByPautaId(pauta.getId());

        Integer total = votosByPauta.size();

        Integer totalSim = (int) votosByPauta.stream()
                .filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
                .count();

        Integer totalNao = total - totalSim;

        return VotacaoDto.builder()
                .pauta(pauta)
                .totalVotos(total)
                .totalSessoes(totalSessoes.intValue())
                .totalSim(totalSim)
                .totalNao(totalNao)
                .build();
    }

}
