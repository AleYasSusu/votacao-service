package br.com.votacao.controller;

import br.com.votacao.domain.VotacaoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface VotingController {

    @Operation(
            summary = "Get voting Result",
            description = "Get Result voting by is used to get the votes from a voting in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    VotacaoDto findVotosByPautaId(@PathVariable Long id);
}
