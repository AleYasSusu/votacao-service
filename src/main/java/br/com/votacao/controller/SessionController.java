package br.com.votacao.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SessionController {

    @Operation(
            summary = "Abrir sessão de votação",
            description = "Este endpoint é usado para abrir uma sessão de votação para uma determinada pauta."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão de votação aberta com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    ResponseEntity<String> openVotingSession(@RequestParam Long agendaId,
                                             @RequestParam(required = false) Long durationMinutes);



}
