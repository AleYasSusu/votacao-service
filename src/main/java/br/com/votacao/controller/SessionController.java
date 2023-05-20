package br.com.votacao.controller;

import br.com.votacao.model.Pauta;
import br.com.votacao.model.Session;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

public interface SessionController {
    @Operation(
            summary = "Get ALL Sessions REST API",
            description = "Get ALL Sessions REST API is used to get all sessions from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    List<Session> findAll();


    @Operation(
            summary = "Create Session REST API",
            description = "Create Session REST API is used to save session in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    Session createSession(@RequestBody Pauta pauta,
                          @RequestParam(required = false) Long minutosValidade);

    @Operation(
            summary = "Find Session",
            description = "Get Session by  ID is used to get single session in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    Session findSessaoById(@PathVariable Long id);

    @Operation(
            summary = "Find Session by Pauta",
            description = "Get Session by Pauta ID is used to get sessions from a single pauta in the database."
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    Session findSessaoByIdAndPautaId(@PathVariable Long id, @PathVariable Long idSessao);

    @Operation(
            summary = "DELETE Session REST API",
            description = "Delete Session REST API is used to delete a particular session from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    void delete(@PathVariable Long id);
}
