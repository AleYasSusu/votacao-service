package br.com.votacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Table(name = "vote")
public class Vote implements Serializable {
    @Id
    @SequenceGenerator(name = "vote_seq", sequenceName = "svote_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_seq")
    private Long id;

    @NotBlank(message = "voto-1")
    private String cpf;

    @NotNull(message = "voto-2")
    private Boolean escolha;

    @NotNull(message = "voto-3")
    @ManyToOne(fetch = FetchType.EAGER)
    private Pauta pauta;

    @JsonIgnore
    public boolean isNew() {
        return getId() == null;
    }

    @JsonIgnore
    public boolean alreadyExist() {
        return getId() != null;
    }
}
