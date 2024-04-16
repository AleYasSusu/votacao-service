package br.com.votacao.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class VotacaoResultadoDTO {
    private int yesVotes;
    private int noVotes;
    private String winner;
}
