package br.com.votacao.controller.impl;

import br.com.votacao.controller.SessionController;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/sessions")
public class SessionControllerImpl implements SessionController {

	private final SessionService sessionService;

	@PostMapping("/abrir-sessao")
	public ResponseEntity<String> openSession(@RequestParam Long pautaId,
											  @RequestParam(required = false) Long durationMinutes) {
		sessionService.abrirSessaoDeVotacao(pautaId, durationMinutes);
		return ResponseEntity.ok("Session opened successfully.");
	}
}

