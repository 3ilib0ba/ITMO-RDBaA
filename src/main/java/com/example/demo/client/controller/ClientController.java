package com.example.demo.client.controller;

import com.example.demo.client.dto.ClientDTO;
import com.example.demo.client.model.Client;
import com.example.demo.client.service.ClientService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {
    private ClientService clientService;
    @Autowired
    public ClientController(
            ClientService clientService
    ) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getUserInfo(@PathVariable("id") Long id) {
        log.info("get /clients/{" + id + "}");
        return ResponseEntity.ok(clientService.getClient(id));
    }

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> addUser(@Valid @RequestBody ClientDTO clientDTO) {
        log.info("post /clients");
        return ResponseEntity.ok(clientService.addUser(clientDTO));
    }

    @GetMapping(value = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> getAllUsers() {
        log.info("get /clients");
        return ResponseEntity.ok(clientService.getAllClients());
    }
}
