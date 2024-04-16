package br.com.votacao.model;


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
    private Long id;

    private LocalDateTime dataInicio;

    private Long minutosValidade;

    @ManyToOne
    @JoinColumn(name = "pauta_id")
    private Pauta pauta;

}
