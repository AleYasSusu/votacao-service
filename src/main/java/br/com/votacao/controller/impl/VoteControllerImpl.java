package br.com.votacao.controller.impl;

import br.com.votacao.controller.VoteController;
import br.com.votacao.dto.VoteRequestDTO;
import br.com.votacao.exception.UnableCpfException;
import br.com.votacao.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/votes")
@RequiredArgsConstructor
public class VoteControllerImpl implements VoteController {

	private final VoteService voteService;

	@PostMapping
	public ResponseEntity<String> receiveVote(@RequestBody VoteRequestDTO voteRequest) {
		try {
			voteService.receiveVote(voteRequest);
			return ResponseEntity.ok("Voto registrado com sucesso.");
		} catch (IllegalStateException | UnableCpfException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}

	@GetMapping("/voting/result/pauta/{sessionId}")
	public String getVotingResultPauta(@PathVariable Long sessionId) {
		return voteService.getResultadoVotacaoPauta(sessionId);
	}
}






