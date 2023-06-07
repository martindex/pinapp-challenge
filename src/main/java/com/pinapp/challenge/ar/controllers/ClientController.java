package com.pinapp.challenge.ar.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.dtos.ClientStatsDto;
import com.pinapp.challenge.ar.services.ClientService;

@RestController
@RequestMapping
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/creacliente")
    @Operation(summary = "Create a new client with properties First Name, Last Name, Age and Date of Birth")
    @ApiResponse(responseCode = "201", description = "Client created")
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/kpideclientes")
    @Operation(summary = "Get client stats with average age of all clients and standard deviation of ages among all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client Stats it s ok",
                    content = @Content(schema = @Schema(implementation = ClientStatsDto.class))),
            @ApiResponse(responseCode = "404", description = "Client Stats have a problem")
    })
    public ResponseEntity<ClientStatsDto> getClientStats() {
        ClientStatsDto clientStatsDto = clientService.getClientStats();
        return ResponseEntity.ok(clientStatsDto);
    }

    @GetMapping("/listclientes")
    @Operation(summary = "Get a list of all clients with estimated death date calculated")
    @ApiResponse(responseCode = "200", description = "List of clients",
            content = @Content(schema = @Schema(implementation = ClientDto.class)))
    public ResponseEntity<List<ClientDto>> getClientListWithEstimatedDeathDate() {
        List<ClientDto> clientList = clientService.getClientListWithEstimatedDeathDate();
        return ResponseEntity.ok(clientList);
    }
}