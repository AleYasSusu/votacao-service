package br.com.votacao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@Table(name = "pauta")
public class Pauta implements Serializable {
    @Id
    @SequenceGenerator(name = "pauta_seq", sequenceName = "pauta_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_seq")
    private Long id;

    @NotBlank(message = "pauta-1")
    private String nome;

    public Pauta(Long pautaId) {
    }
}
