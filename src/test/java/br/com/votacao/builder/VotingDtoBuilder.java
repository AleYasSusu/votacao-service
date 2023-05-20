package br.com.votacao.builder;

import br.com.votacao.domain.VotingDto;

import static br.com.votacao.builder.PautaBuilder.buildPautaDefault;

public class VotingDtoBuilder {

    private VotingDto votingDto;

    public static VotingDtoBuilder buildVotingDtoDefault() {
        VotingDtoBuilder builder = new VotingDtoBuilder();
        builder.votingDto = VotingDto.builder()
                .pauta(buildPautaDefault().build())
                .totalSessions(10)
                .totalVoteYes(1)
                .totalVoteNo(0)
                .totalVotes(1)
                .build();
        return builder;
    }

    public VotingDto build() {
        return votingDto;
    }
}
