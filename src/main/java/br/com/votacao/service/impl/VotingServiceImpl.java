package br.com.votacao.service.impl;

import br.com.votacao.domain.VotingDto;
import br.com.votacao.exception.BusinessException;
import br.com.votacao.exception.VotingNotFoundException;
import br.com.votacao.exception.VoteNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import br.com.votacao.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VotingServiceImpl implements VotingService {

    @Lazy
    private final VoteService voteService;
    @Lazy
    private final SessionService sessionService;

    private final VoteRepository voteRepository;

    @Override
    public Vote save(final Vote voto) {
        verifyIfExists(voto);
        return voteRepository.save(voto);
    }

    private void verifyIfExists(final Vote voto) {
        var votoByCpfAndPauta = voteRepository.findByCpf(voto.getCpf());

        if (votoByCpfAndPauta.isPresent() && (voto.isNew() || isNotUnique(voto, votoByCpfAndPauta.get()))) {
            throw new BusinessException(null, null);
        }
    }

    private boolean isNotUnique(Vote voto, Vote votoByCpfAndPauta) {
        return voto.alreadyExist() && !votoByCpfAndPauta.equals(voto);
    }

    public List<Vote> findAll() {
        return voteRepository.findAll();
    }

    @Override
    public void delete(Vote voto) {
        var votoById =
                voteRepository.findById(voto.getId())
                        .orElseThrow(VoteNotFoundException::new);
        voteRepository.delete(votoById);
    }

    @Override
    public List<Vote> findVotosByPautaId(Long id) {
        return voteService.findByPautaId(id)
                .orElseThrow(VoteNotFoundException::new);
    }

    @Override
    public VotingDto getResultVotacao(Long id) {
        return buildVotingPauta(id);
    }


    public VotingDto buildVotingPauta(Long id) {
        var votosByStaff =
                voteRepository.findByPautaId(id)
                        .orElseThrow(VotingNotFoundException::new);

        Pauta pauta = votosByStaff.iterator().next().getPauta();

        Long totalSessions = sessionService.countSessionByPautaId(pauta.getId());

        Integer total = votosByStaff.size();

        Integer totalVoteYes = (int) votosByStaff.stream().filter(voto -> Boolean.TRUE.equals(voto.getEscolha()))
                .count();

        Integer totalVoteNo = total - totalVoteYes;

        return VotingDto.builder()
                .pauta(pauta)
                .totalVotes(total)
                .totalSessions(totalSessions.intValue())
                .totalVoteYes(totalVoteYes)
                .totalVoteNo(totalVoteNo)
                .build();
    }
}