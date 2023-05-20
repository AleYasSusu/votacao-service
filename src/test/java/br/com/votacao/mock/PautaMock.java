package br.com.votacao.mock;

import br.com.votacao.model.Pauta;

public class PautaMock {
    public static Pauta buildPauta(){
        return Pauta.builder()
                .id(1L)
                .nome("teste ti")
                .build();
    }
}
