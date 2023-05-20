package br.com.votacao.builder;


import br.com.votacao.model.Pauta;

import java.util.List;

import static java.util.Collections.singletonList;

public class PautaBuilder {

    private Pauta pauta;

    public static PautaBuilder buildPautaDefault() {
        PautaBuilder builder = new PautaBuilder();
        builder.pauta = Pauta.builder()
                .id(10L)
                .nome("Test")
                .build();
        return builder;
    }

    public Pauta build() {
        return pauta;
    }

    public List<Pauta> buildList() {
        return singletonList(pauta);
    }
}
