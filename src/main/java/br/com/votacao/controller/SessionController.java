package br.com.votacao.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public interface SessionController {

    @Operation(
            summary = "Get ALL Pautas REST API",
            description = "Get ALL Pautas REST API is used to get all pautas from the database"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status 200 SUCESS"
    )
    ResponseEntity<String> openSession(@RequestParam Long agendaId,
                                       @RequestParam(required = false) Long durationMinutes);


}
