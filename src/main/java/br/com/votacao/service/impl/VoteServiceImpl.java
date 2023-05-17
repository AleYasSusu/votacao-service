package br.com.votacao.service.impl;

import br.com.votacao.domain.CpfValidationDto;
import br.com.votacao.exception.InvalidCpfException;
import br.com.votacao.exception.InvalidSessionException;
import br.com.votacao.exception.SessaoTimeOutException;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.exception.VotoAlreadyExistsException;
import br.com.votacao.exception.VotoNotFoundException;
import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;
import br.com.votacao.repository.VoteRepository;
import br.com.votacao.service.SessionService;
import br.com.votacao.service.VoteService;
import br.com.votacao.service.VotingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

@Service
public class VoteServiceImpl implements VoteService {

    private static final String CPF_NOT_ABLE_TO_VOTE = "UNABLE_TO_VOTE";
    private final VoteRepository voteRepository;
    private final RestTemplate restTemplate;
    private final SessionService sessionService;
    private final VotingService votingService;
    @Value("${app.integracao.cpf.url}")
    private String urlCpfValidator;

    @Autowired
    public VoteServiceImpl(RestTemplate restTemplate,
                           VoteRepository voteRepository,
                           SessionService sessionService,
                           VotingService votingService) {

        this.restTemplate = restTemplate;
        this.voteRepository = voteRepository;
        this.sessionService = sessionService;
        this.votingService = votingService;
    }




    @Override
    public Vote findById(Long id) {
        Optional<Vote> findById = voteRepository.findById(id);
        if (!findById.isPresent()) {
            throw new VotoNotFoundException();
        }
        return findById.get();
    }

    private Cache<String, ResponseEntity<CpfValidationDto>> cache;

    @Override
    public void initialize() {
        cache = Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build();
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

    private void verifyVote(final Session session, final Vote vote) {
        LocalDateTime dataLimite = session.getDataInicio().plusMinutes(session.getMinutosValidade());
        if (LocalDateTime.now().isAfter(dataLimite)) {
            throw new SessaoTimeOutException();
        }

        cpfAbleToVote(vote);
        votoAlreadyExists(vote);
    }

    private void votoAlreadyExists(final Vote vote) {
        Optional<Vote> voteByCpfAndPauta = voteRepository
                .findByCpfAndPautaId(vote.getCpf(), vote.getPauta().getId());
        if (voteByCpfAndPauta.isPresent()) {
            throw new VotoAlreadyExistsException();
        }
    }

    private void cpfAbleToVote(final Vote voto) {
        ResponseEntity<CpfValidationDto> cpfValidation = getCpfValidation(voto);
        if (cpfValidation.getStatusCode().is2xxSuccessful()) {
            if (CPF_NOT_ABLE_TO_VOTE.equalsIgnoreCase(Objects.requireNonNull(cpfValidation.getBody())
                    .getStatus())) {
                throw new UnableCpfException();
            }
        } else {
            throw new InvalidCpfException();
        }
    }

    private ResponseEntity<CpfValidationDto> getCpfValidation(final Vote voto) {
        // Tenta obter a validação do CPF no cache
        String cacheKey = "cpf-validation:" + voto.getCpf();
        ResponseEntity<CpfValidationDto> cachedResult = cache.getIfPresent(cacheKey);

        if (cachedResult != null) {
            return cachedResult;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<CpfValidationDto> response = restTemplate.exchange(urlCpfValidator.concat("/")
                .concat(voto.getCpf()), HttpMethod.GET, entity, CpfValidationDto.class);

        // Armazena a validação do CPF em cache por 5 minutos
        cache.put(cacheKey, response);

        return response;
    }

    @Override
    public List<Vote> findVotosByPautaId(Long id) {
        Optional<List<Vote>> findByPautaId = voteRepository.findByPautaId(id);

        if (!findByPautaId.isPresent()) {
            throw new VotoNotFoundException();
        }

        return findByPautaId.get();
    }

    @Override
    public void delete(Long id) {
        Optional<Vote> votoById = voteRepository.findById(id);
        if (!votoById.isPresent()) {
            throw new VotoNotFoundException();
        }
        voteRepository.delete(votoById.get());
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
