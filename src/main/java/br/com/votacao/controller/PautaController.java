package br.com.votacao.controller;

import br.com.votacao.model.Pauta;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

public interface PautaController {
    @Operation(
            summary = "Get ALL Pautas REST API",
            description = "Get ALL Pautas REST API is used to get all pautas from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    List<Pauta> findAll();

    @Operation(
            summary = "Create Pauta REST API",
            description = "Create Pauta REST API is used to save Pauta in database"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status 201 CREATED"
    )
    Pauta createNewPauta(@Valid @RequestBody Pauta pauta);

    @Operation(
            summary = "Get Pauta by id REST API",
            description = "Get Pauta by ID REST API is used to get single pauta from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    Pauta findById(@PathVariable Long id);

    @Operation(
            summary = "DELETE Pauta REST API",
            description = "Delete Pauta REST API is used to delete a particular pauta from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    void delete(@PathVariable Long id);
}
