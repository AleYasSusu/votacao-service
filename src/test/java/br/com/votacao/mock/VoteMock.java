package br.com.votacao.mock;

import br.com.votacao.model.Vote;

public class VoteMock {
    public static Vote buildVoto() {
        return Vote.builder()
                .id(1L)
                .escolha("Sim")
                .cpf("86130113064")
                .session(SessionMock.buildSession())
                .build();
    }
}