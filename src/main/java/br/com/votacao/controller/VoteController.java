package br.com.votacao.controller;

import br.com.votacao.dto.VoteRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface VoteController {

    @Operation(
            summary = "Post votar em uma sessão de votação aberta",
            description = "O endpoint Post, usado para registrar votos em uma sessão de votação aberta."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida (OK)"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "UNAUTHORIZED",
                    content = @Content)
    })
    ResponseEntity<String> receiveVote(@RequestBody @Valid VoteRequestDTO requestDTO);

    @Operation(
            summary = "Obter todos os votos de uma sessão de votação",
            description = "endpoint Get usado para obter todos os votos de uma sessão de votação esteja encerrada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Requisição bem-sucedida (OK)")
    })
    String getVotingResultPauta(@PathVariable Long sessionId);


}
