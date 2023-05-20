package br.com.votacao.domain;

import br.com.votacao.model.Pauta;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class VotingDto implements Serializable {
	private static final long serialVersionUID = -6641295645471857940L;
	
	private Pauta pauta;
	private Integer totalVoteYes;
	private Integer totalVoteNo;
	private Integer totalVotes;
	private Integer totalSessions;
}
