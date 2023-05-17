package br.com.votacao.controller;

import br.com.votacao.model.Vote;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface VoteController {

    @Operation(
            summary = "Create Vote REST API",
            description = "Create Vote REST API is used to save vote in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    Vote createNewVote(@PathVariable Long idPauta, @PathVariable Long idSessao, @RequestBody Vote voto);

    @Operation(
            summary = "Find Vote",
            description = "Get Vote by ID REST API is used to get single vote from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    Vote findVotoById(@PathVariable Long id);

    @Operation(
            summary = "Find Vote por session",
            description = "Get Vote by Session ID is used to get votes from a single session in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    List<Vote> findVotoBySessaoId(@PathVariable Long id);

    @Operation(
            summary = "DELETE Vote REST API",
            description = "Delete Vote REST API is used to delete a particular vote from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    void delete(@PathVariable Long id);
}
