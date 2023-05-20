package br.com.votacao.mock;


import br.com.votacao.model.Vote;

public class VoteMock {

    public static Vote buildVoto(){
        return Vote.builder()
                .cpf("1234")
                .id((long) 1)
                .escolha(true)
                .pauta(PautaMock.buildPauta())
                .build();
    }
}
