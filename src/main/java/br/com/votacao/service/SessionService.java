package br.com.votacao.service;

import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;

import java.util.List;

public interface SessionService {

    Session abrirSessaoDeVotacao(Long pautaId, Long minutosValidade);

    Session findById(Long id);
}
