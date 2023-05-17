package br.com.votacao.controller.impl;

import br.com.votacao.controller.SessionController;
import br.com.votacao.model.Session;
import br.com.votacao.service.impl.SessionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/sessions")
public class SessionControllerImpl implements SessionController {

	private final SessionServiceImpl sessaoServiceImpl;

	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Session> findAll() {
		return sessaoServiceImpl.findAll();
	}

	@Override
	@PostMapping("/pautas/{id}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Session createSession(@PathVariable Long id, @Valid @RequestBody Session session) {
		return sessaoServiceImpl.createSession(id, session);
	}
	
	@Override
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoById(@PathVariable Long id) {
		return sessaoServiceImpl.findById(id);
	}

	@Override
	@GetMapping("/pautas/{id}/sessions/{idSessao}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoByIdAndPautaId(@PathVariable Long idPauta, @PathVariable Long idSession) {
		return sessaoServiceImpl.findByIdAndPautaId(idPauta, idSession);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		sessaoServiceImpl.delete(id);
	}
}
