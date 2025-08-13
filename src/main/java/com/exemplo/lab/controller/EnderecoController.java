package com.exemplo.lab.controller;

import com.exemplo.lab.dto.EnderecoDTO;
import com.exemplo.lab.service.EnderecoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @GetMapping("/{cep}")
    public EnderecoDTO get(@PathVariable String cep){
        return service.porCep(cep);
    }
}
