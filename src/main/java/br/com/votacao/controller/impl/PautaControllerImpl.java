package br.com.votacao.controller.impl;

import br.com.votacao.controller.PautaController;
import br.com.votacao.model.Pauta;
import br.com.votacao.service.PautaService;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/pautas")
@RequiredArgsConstructor
public class PautaControllerImpl implements PautaController {

	private final PautaService pautaService;

	@Override
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public List<Pauta> findAll() {
		return pautaService.findAllPautas();
	}

	@PostMapping
	@Override
	@ResponseStatus(code = HttpStatus.CREATED)
	public Pauta createNewPauta(@Valid @RequestBody Pauta pauta) {
		return pautaService.createNewPauta(pauta);
	}

	@Override
	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Pauta findById(@PathVariable Long id) {
		return pautaService.findById(id);
	}

	@Override
	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public void delete(@PathVariable Long id) {
		pautaService.delete(id);
	}
}
