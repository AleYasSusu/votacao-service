package br.com.votacao.model;

import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "ID da pauta", example = "1")
    private Long id;

    @NotBlank(message = "pauta-1")
    @Schema(description = "Nome da pauta", example = "Nome da Pauta")
    private String nome;


}
