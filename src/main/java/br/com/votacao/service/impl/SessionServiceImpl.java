package br.com.votacao.service.impl;

import br.com.votacao.exception.SessaoNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.repository.SessionRepository;
import br.com.votacao.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private static final long DEFAULT_DURATION_MINUTES = 1;
    private final SessionRepository sessionRepository;

    @Override
    public List<Session> findAll() {
        return sessionRepository.findAll();
    }

    @Override
    public Session createSession(Pauta pauta, Long minutosValidade) {
        LocalDateTime dataInicio = LocalDateTime.now();
        Long duracaoMinutos = minutosValidade != null ? minutosValidade : DEFAULT_DURATION_MINUTES;

       var session =  Session.builder()
                .dataInicio(dataInicio)
                .minutosValidade(duracaoMinutos)
                .pauta(pauta)
                .build();

        return sessionRepository.save(session);
    }


    @Override
    public void delete(Long id) {
        if (!sessionRepository.existsById(id)) {
            throw new SessaoNotFoundException();
        }
        sessionRepository.deleteById(id);
    }

    @Override
    public void deleteByPautaId(Long id) {
        sessionRepository.deleteByPautaId(id);
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(SessaoNotFoundException::new);
    }

    @Override
    public Session findByIdAndPautaId(Long idSessao, Long pautaId) {
        return sessionRepository.findByIdAndPautaId(idSessao, pautaId)
                .orElseThrow(SessaoNotFoundException::new);
    }

    @Override
    public Long countSessionByPautaId(Long pautaId) {
        return sessionRepository.countByPautaId(pautaId);
    }
}
