package com.pinapp.challenge.ar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.pinapp.challenge.ar.dtos.ClientDto;
import com.pinapp.challenge.ar.services.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    @Operation(summary = "Create a new client")
    @ApiResponse(responseCode = "201", description = "Client created")
    public ResponseEntity<Void> createClient(@RequestBody ClientDto clientDto) {
        clientService.createClient(clientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get client details by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = @Content(schema = @Schema(implementation = ClientDto.class))),
            @ApiResponse(responseCode = "404", description = "Client not found")
    })
    public ResponseEntity<ClientDto> getClient(@PathVariable Long id) {
        ClientDto clientDto = clientService.getClient(id);
        return ResponseEntity.ok(clientDto);
    }

    @GetMapping("/list")
    @Operation(summary = "Get a list of all clients")
    @ApiResponse(responseCode = "200", description = "List of clients",
            content = @Content(schema = @Schema(implementation = ClientDto.class)))
    public ResponseEntity<List<ClientDto>> getClientList() {
        List<ClientDto> clientList = clientService.getClientList();
        return ResponseEntity.ok(clientList);
    }
}