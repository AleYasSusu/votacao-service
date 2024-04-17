package br.com.votacao.mock;


import br.com.votacao.model.Session;

import java.time.LocalDateTime;

public class SessionMock {

    public static Session buildSession(){
        return Session.builder()
                .id(1L)
                .dataInicio(LocalDateTime.of(2024, 4, 16, 12, 0, 0))
                .minutosValidade(45L)
                .pauta(PautaMock.buildPauta())
                .build();
    }

}