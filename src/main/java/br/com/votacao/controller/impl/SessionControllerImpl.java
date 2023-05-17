package br.com.votacao.controller.impl;

import br.com.votacao.controller.SessionController;
import br.com.votacao.model.Session;
import br.com.votacao.service.impl.SessionServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SessionControllerImpl implements SessionController {

	private final SessionServiceImpl sessaoServiceImpl;

	@Override
	@GetMapping("v1/pautas/sessoes")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Session> all() {
		return sessaoServiceImpl.findAll();
	}

	@Override
	@PostMapping("v1/pautas/{id}/sessoes")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Session createSession(@PathVariable Long id, @Valid @RequestBody Session session) {
		return sessaoServiceImpl.createSession(id, session);
	}
	
	@Override
	@GetMapping("v1/pautas/sessoes/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoById(@PathVariable Long id) {
		return sessaoServiceImpl.findById(id);
	}

	@Override
	@GetMapping("v1/pautas/{id}/sessoes/{idSessao}")
	@ResponseStatus(code = HttpStatus.OK)
	public Session findSessaoByIdAndPautaId(@PathVariable Long id, @PathVariable Long idSessao) {
		return sessaoServiceImpl.findByIdAndPautaId(idSessao, id);
	}

	@Override
	@DeleteMapping("v1/pautas/sessoes/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		sessaoServiceImpl.delete(id);
	}
}
