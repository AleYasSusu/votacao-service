package br.com.votacao.service;

import br.com.votacao.model.Session;

public interface SessionService {

    Session abrirSessaoDeVotacao(Long pautaId, Long minutosValidade);

    Session findById(Long id);
}
