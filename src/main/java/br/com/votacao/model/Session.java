package br.com.votacao.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "session")
public class Session implements Serializable {
    @Id
    @SequenceGenerator(name = "sessao_seq", sequenceName = "sessao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq")
    @Schema(description = "ID da sessão", example = "1")
    private Long id;

    @Schema(description = "Data de início da sessão", example = "2024-04-17T12:00:00")
    private LocalDateTime dataInicio;

    @Schema(description = "Minutos de validade da sessão", example = "60")
    private Long minutosValidade;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    @Schema(description = "Pauta relacionada à sessão")
    private Pauta pauta;

}
