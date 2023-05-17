package br.com.votacao.service;

import br.com.votacao.model.Session;

import java.util.List;

public interface SessionService {
    List<Session> findAll();

    Session createSession(Long id, Session session);

    void delete(Long id);

    void deleteByPautaId(Long id);

    Session findById(Long id);

    Session findByIdAndPautaId(Long idSessao, Long pautaId);

    Long countSessoesByPautaId(Long pautaId);
}
