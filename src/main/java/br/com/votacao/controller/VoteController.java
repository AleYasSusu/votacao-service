package br.com.votacao.controller;

import br.com.votacao.dto.VoteRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface VoteController {

    @Operation(
            summary = "Get ALL Pautas REST API",
            description = "Get ALL Pautas REST API is used to get all pautas from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    ResponseEntity<String> receiveVote(@RequestBody @Valid VoteRequestDTO requestDTO);

    @Operation(
            summary = "Get ALL Pautas REST API",
            description = "Get ALL Pautas REST API is used to get all pautas from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    String getVotingResultPauta(@PathVariable Long sessionId);

}
