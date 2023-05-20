package br.com.votacao.controller.impl;

import br.com.votacao.controller.SessionController;
import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import br.com.votacao.service.SessionService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/sessions")
public class SessionControllerImpl implements SessionController {

	private final SessionService sessionService;

	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Session> findAll() {
		return sessionService.findAll();
	}


	@Override
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Session createSession(@RequestBody Pauta pauta,
								 @RequestParam(required = false) Long minutosValidade) {
		return sessionService.createSession(pauta, minutosValidade);
	}
	
	@Override
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoById(@PathVariable Long id) {
		return sessionService.findById(id);
	}

	@Override
	@GetMapping("/pautas/{id}/sessions/{idSessao}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoByIdAndPautaId(@PathVariable Long idPauta, @PathVariable Long idSession) {
		return sessionService.findByIdAndPautaId(idPauta, idSession);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		sessionService.delete(id);
	}
}
