package br.com.votacao.controller.impl;

import br.com.votacao.controller.VoteController;
import br.com.votacao.model.Vote;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/votes")
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

	private final VoteService voteService;

	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Vote> findAll() {
		return voteService.findAll();
	}

	@Override
	@PostMapping("/pautas/{idPauta}/sessions/{idSessao}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Vote createNewVote(Long idPauta, Long idSessao, Vote voto) {
		return voteService.createNewVote(idPauta, idSessao, voto);
	}

	@Override
	@GetMapping("{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Vote findVotoById(@PathVariable Long id) {
		return voteService.findById(id);
	}

	@Override
	@GetMapping("/pautas/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Vote> findVotoBySessaoId(@PathVariable Long id) {
		return voteService.findVotosByPautaId(id);
	}

}
