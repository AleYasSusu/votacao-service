package br.com.votacao.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class VoteRequestDTO {
    private Long sessionId;
    private String cpf;
    private String escolha;



}
