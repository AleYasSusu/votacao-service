package br.com.votacao.controller.impl;

import br.com.votacao.controller.VotingController;
import br.com.votacao.domain.VotacaoDto;
import br.com.votacao.service.VotingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/votings")
@RequiredArgsConstructor
public class VoingControllerImpl  implements VotingController {

	private final VotingService votingService;


	@Override
	@GetMapping("/{id}/voting")
	@ResponseStatus(code = HttpStatus.OK)
	public VotacaoDto findVotosByPautaId(@PathVariable Long id) {
		return votingService.getResultVotacao(id);
	}
}
