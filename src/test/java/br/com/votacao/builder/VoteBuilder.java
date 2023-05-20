package br.com.votacao.builder;

import br.com.votacao.model.Session;
import br.com.votacao.model.Vote;

import java.util.Collections;
import java.util.List;

import static br.com.votacao.builder.PautaBuilder.buildPautaDefault;

public class VoteBuilder {

    private Vote vote;

    public static VoteBuilder buildVoteDefault() {
        VoteBuilder builder = new VoteBuilder();
        builder.vote = Vote.builder()
                .id(10L)
                .cpf("999.999.999-99")
                .escolha(true)
                .pauta(buildPautaDefault().build())
                .build();
        return builder;
    }

    public Vote build() {
        return vote;
    }
    public List<Vote> buildList() {
        return Collections.singletonList(vote);
    }
}