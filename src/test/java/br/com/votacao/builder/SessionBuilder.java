package br.com.votacao.builder;


import br.com.votacao.model.Session;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static br.com.votacao.builder.PautaBuilder.buildPautaDefault;


public class SessionBuilder {

    private Session session;

    public static SessionBuilder buildSessionDefault() {
        SessionBuilder builder = new SessionBuilder();
        builder.session = Session.builder()
                .pauta(buildPautaDefault().build())
                .id(10L)
                .dataInicio(LocalDateTime.now())
                .minutosValidade(20L)
                .build();
        return builder;
    }

    public Session build() {
        return session;
    }

    public List<Session> buildList() {
        return Collections.singletonList(session);
    }
}
