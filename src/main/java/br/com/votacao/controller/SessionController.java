package br.com.votacao.controller;

import br.com.votacao.model.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

public interface SessionController {
    @ApiOperation(value = "Get Sessoes")
    @GetMapping("v1/pautas/sessoes")
    @ResponseStatus(code = HttpStatus.OK)
    List<Session> all();

    @ApiOperation(value = "Create Sessao")
    @PostMapping("v1/pautas/{id}/sessoes")
    @ResponseStatus(code = HttpStatus.CREATED)
    Session createSession(@PathVariable Long id, @Valid @RequestBody Session session);

    @ApiOperation(value = "Find Sessao")
    @GetMapping("v1/pautas/sessoes/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    Session findSessaoById(@PathVariable Long id);

    @ApiOperation(value = "Find Sessao por Pauta")
    @GetMapping("v1/pautas/{id}/sessoes/{idSessao}")
    @ResponseStatus(code = HttpStatus.OK)
    Session findSessaoByIdAndPautaId(@PathVariable Long id, @PathVariable Long idSessao);

    @ApiOperation(value = "Delete Sessao")
    @DeleteMapping("v1/pautas/sessoes/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    void delete(@PathVariable Long id);
}
