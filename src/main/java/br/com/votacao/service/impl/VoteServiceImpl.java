package br.com.votacao.service.impl;

import br.com.votacao.domain.CpfValidationDto;
import br.com.votacao.exception.*;
import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import br.com.votacao.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private static final String CPF_NOT_ABLE_TO_VOTE = "UNABLE_TO_VOTE";
    private final VoteRepository voteRepository;
    private final RestTemplate restTemplate;
    @Lazy
    private final SessionService sessionService;
    @Lazy
    private final VotingService votingService;
    @Value("${app.integracao.cpf.url}")
    private String urlCpfValidator = "";


    @Override
    public Vote findById(Long id) {
        Optional<Vote> findById = voteRepository.findById(id);
        if (findById.isEmpty()) {
            throw new VoteNotFoundException();
        }
        return findById.get();
    }

    @Override
    public List<Vote> findAll() {
        return voteRepository.findAll();
    }


    @Override
    public Vote createNewVote(Long idPauta, Long idSessao, Vote voto) {
        Session session = sessionService.findByIdAndPautaId(idSessao, idPauta);
        if (!idPauta.equals(session.getPauta().getId())) {
            throw new InvalidSessionException();
        }
        voto.setPauta(session.getPauta());
        return saveAndVerify(session, voto);
    }

    public Vote saveAndVerify(final Session session, final Vote voto) {
        verifyVote(session, voto);
        return voteRepository.save(voto);
    }

    public void verifyVote(final Session session, final Vote vote) {
        LocalDateTime dataLimite = session.getDataInicio().plusMinutes(session.getMinutosValidade());
        if (LocalDateTime.now().isAfter(dataLimite)) {
            throw new SessionTimeOutException();
        }
        cpfAbleToVote(vote);
        voteAlreadyExists(vote);
    }

    public void voteAlreadyExists(final Vote vote) {
        Optional<Vote> voteByCpfAndPauta = voteRepository
                .findByCpfAndPautaId(vote.getCpf(), vote.getPauta().getId());
        if (voteByCpfAndPauta.isPresent()) {
            throw new VoteAlreadyExistsException();
        }
    }

    @Override
    public void cpfAbleToVote(Vote vote) {
        try {
            var cpfValidation = getCpfValidation(vote);
            if (HttpStatus.OK.equals(cpfValidation.getStatusCode())) {
                if (isCpfNotAbleToVote(cpfValidation)) {
                    throw new UnableCpfException("CPF não está habilitado para votar");
                }
            } else {
                throw new InvalidCpfException();
            }
        } catch (CpfValidationException e) {
            throw new UnableCpfException("Erro na validação do CPF", e);
        }
    }

    private boolean isCpfNotAbleToVote(ResponseEntity<CpfValidationDto> cpfValidation) {
        return CPF_NOT_ABLE_TO_VOTE.equalsIgnoreCase(Objects.requireNonNull(cpfValidation.getBody()).getStatus());
    }

    private ResponseEntity<CpfValidationDto> getCpfValidation(final Vote voto) {
        String cpfUrl = "/" + voto.getCpf();
        URI uri = UriComponentsBuilder.fromUriString(urlCpfValidator)
                .path(cpfUrl)
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            return restTemplate.exchange(uri, HttpMethod.GET, entity, CpfValidationDto.class);
        } catch (RestClientException e) {
            throw new CpfValidationException("Erro ao obter validação do CPF", e);
        }
    }

    @Override
    public List<Vote> findVotosByPautaId(Long id) {
        Optional<List<Vote>> findByPautaId = voteRepository.findByPautaId(id);

        if (findByPautaId.isEmpty()) {
            throw new VoteNotFoundException();
        }

        return findByPautaId.get();
    }

    @Override
    public void deleteByPautaId(Long id) {
        Optional<List<Vote>> votos = voteRepository.findByPautaId(id);
        votos.ifPresent(voteRepository::deleteAll);
    }

    @Override
    public Optional<Vote> findByCpf(String cpf) {
        return voteRepository.findByCpf(cpf);
    }

    @Override
    public Optional<List<Vote>> findByPautaId(Long id) {
        return voteRepository.findByPautaId(id);
    }
}
