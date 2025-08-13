package com.exemplo.lab.controller;

import com.exemplo.lab.dto.ClienteDTO;
import com.exemplo.lab.model.Cliente;
import com.exemplo.lab.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody @Valid ClienteDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping("/{id}")
    public Cliente get(@PathVariable Long id){
        return service.buscar(id);
    }
}
