package br.com.votacao.service;

import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    Session createSession(Pauta pauta, Long minutosValidade);

    void delete(Long id);

    void deleteByPautaId(Long id);

    Session findById(Long id);

    Session findByIdAndPautaId(Long idSessao, Long pautaId);

    Long countSessionByPautaId(Long pautaId);
}
