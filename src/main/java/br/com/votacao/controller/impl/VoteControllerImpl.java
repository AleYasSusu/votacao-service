package br.com.votacao.controller.impl;

import br.com.votacao.controller.VoteController;
import br.com.votacao.model.Vote;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/votes")
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

	private final VoteService voteService;

	@GetMapping("/pautas/sessoes/votos")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<List<Vote>> findAll() {
		List<Vote> allVote = voteService.findAll();
		return new ResponseEntity<>(allVote, HttpStatus.OK);
	}

	@Override
	@PostMapping("/pautas/{idPauta}/sessoes/{idSessao}/votos")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Vote createNewVote(Long idPauta, Long idSessao, Vote voto) {
		return voteService.createNewVote(idPauta, idSessao, voto);
	}

	@Override
	@GetMapping("/pautas/sessoes/votos/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Vote findVotoById(@PathVariable Long id) {
		return voteService.findById(id);
	}

	@Override
	@GetMapping("/pautas/{id}/sessoes/votos")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Vote> findVotoBySessaoId(@PathVariable Long id) {
		return voteService.findVotosByPautaId(id);
	}

	@Override
	@DeleteMapping("/pautas/sessoes/votos/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		voteService.delete(id);
	}
}
