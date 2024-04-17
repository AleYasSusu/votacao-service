package br.com.votacao.service.impl;


import br.com.votacao.exception.SessionTimeOutException;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.exception.VoteAlreadyExistsException;
import br.com.votacao.exception.VoteNotFoundException;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.MockCpfService;
import br.com.votacao.dto.VoteRequestDTO;

import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {
    private final VoteRepository voteRepository;
    private final SessionService sessionService;
    private final MockCpfService cpfServiceClient;

    //private final CPFServiceClient cpfServiceClient;

    @Override
    public void receiveVote(VoteRequestDTO voteRequest) {
        verifySessionValidity(voteRequest.getSessionId());
        verifyVotingAbility(voteRequest.getCpf());

        boolean escolha = parseEscolha(voteRequest.getEscolha());

        verifyVoterAlreadyVoted(voteRequest.getCpf(), voteRequest.getSessionId());

        saveVote(voteRequest.getCpf(), escolha, voteRequest.getSessionId());
    }

    private void verifySessionValidity(Long sessionId) {
        Session session = sessionService.findById(sessionId);
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime sessionEndTime = session.getDataInicio().plusMinutes(session.getMinutosValidade());
        if (currentTime.isBefore(session.getDataInicio()) || currentTime.isAfter(sessionEndTime)) {
            throw new SessionTimeOutException("Sessão de votação não está aberta ou expirou.");
        }
    }

    private void verifyVotingAbility(String cpf) {
        //String votingAbility = cpfServiceClient.checkVotingAbility(cpf);
        String votingAbility = cpfServiceClient.checkVotingAbility(cpf);
        if (!"ABLE_TO_VOTE".equals(votingAbility)) {
            throw new UnableCpfException("Associado não está habilitado para votar.");
        }
    }

    private boolean parseEscolha(String escolha) {
        if ("Sim".equalsIgnoreCase(escolha) || "S".equalsIgnoreCase(escolha)) {
            return true;
        } else if ("Não".equalsIgnoreCase(escolha) || "Nao".equalsIgnoreCase(escolha) || "NÃO".equalsIgnoreCase(escolha)
                || "NAO".equalsIgnoreCase(escolha) || "N".equalsIgnoreCase(escolha)) {
            return false;
        } else {
            throw new IllegalArgumentException("Escolha inválida. A escolha deve ser 'Sim' ou 'Não'.");
        }
    }
    
    private void verifyVoterAlreadyVoted(String cpf, Long sessionId) {
        if (voteRepository.existsByCpfAndSessionId(cpf, sessionId)) {
            throw new VoteAlreadyExistsException("Já foi computado um Voto para está Pauta com este número deeste CPF.");
        }
    }

    private void saveVote(String cpf, boolean escolha, Long sessionId) {
        Vote vote = new Vote();
        vote.setCpf(cpf);
        vote.setEscolha(String.valueOf(escolha));
        vote.setSession(sessionService.findById(sessionId));
        voteRepository.save(vote);
    }

    @Override
    public String getResultadoVotacaoPauta(Long sessionId) {
        List<Vote> votes = voteRepository.findBySessionId(sessionId);
        if (votes.isEmpty()) {
            throw new VoteNotFoundException("Não foram encontrados votos para a sessão com ID: " + sessionId);
        }

        Session session = votes.get(0).getSession();
        Pauta pauta = session.getPauta();

        int yesVotes = 0;
        int noVotes = 0;


        if (session.getMinutosValidade() == 0) {
            for (Vote vote : votes) {
                if (Boolean.parseBoolean(vote.getEscolha())) {
                    yesVotes++;
                } else {
                    noVotes++;
                }
            }

            String resultado;
            if (yesVotes > noVotes) {
                resultado = "aprovada";
            } else if (noVotes > yesVotes) {
                resultado = "desaprovada";
            } else {
                resultado = "empatada";
            }

            return "A pauta '" + pauta.getNome() + "' teve um total de " + yesVotes + " votos 'Sim' e um total de "
                    + noVotes + " votos 'Não', então ela está " + resultado + ".";
        } else {
            return "A votação para a pauta " + pauta.getNome() + " ainda está em andamento não possui resultado.";
        }
    }
}
