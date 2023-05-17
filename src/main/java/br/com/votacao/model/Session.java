package br.com.votacao.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_session")
public class Session implements Serializable {
    @Id
    @SequenceGenerator(name = "sessao_seq", sequenceName = "sessao_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sessao_seq")
    private Long id;

    private LocalDateTime dataInicio;

    private Long minutosValidade;

    @ManyToOne(fetch = FetchType.EAGER)
    private Pauta pauta;

    public Session pauta(Pauta pauta) {
        this.pauta = pauta;
        return this;
    }
}
