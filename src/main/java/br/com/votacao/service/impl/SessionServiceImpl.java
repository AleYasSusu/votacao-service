package br.com.votacao.service.impl;


import br.com.votacao.exception.SessionNotFoundException;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.repository.SessionRepository;
import br.com.votacao.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private static final long DEFAULT_DURATION_MINUTES = 1;
    private final SessionRepository sessionRepository;

    @Override
    public Session abrirSessaoDeVotacao(Long pautaId, Long minutosValidade) {
        LocalDateTime dataInicio = LocalDateTime.now();
        Long duracaoMinutos = minutosValidade != null && minutosValidade > 0 ? minutosValidade : DEFAULT_DURATION_MINUTES;

        return saveDesssion(pautaId, dataInicio, duracaoMinutos);
    }

    private Session saveDesssion(Long pautaId, LocalDateTime dataInicio, Long duracaoMinutos) {
        Pauta pauta = new Pauta();
        pauta.setId(pautaId);

        Session.SessionBuilder builder = Session.builder();
        builder.dataInicio(dataInicio);
        builder.minutosValidade(duracaoMinutos);
        builder.pauta(pauta);
        Session session = builder
                .build();

        return sessionRepository.save(session);
    }

    @Override
    public Session findById(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(SessionNotFoundException::new);
    }
}
