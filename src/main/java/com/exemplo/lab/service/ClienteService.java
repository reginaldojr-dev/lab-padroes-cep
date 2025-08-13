package com.exemplo.lab.service;

import com.exemplo.lab.dto.ClienteDTO;
import com.exemplo.lab.dto.EnderecoDTO;
import com.exemplo.lab.model.Cliente;
import com.exemplo.lab.model.Endereco;
import com.exemplo.lab.repo.ClienteRepository;
import com.exemplo.lab.repo.EnderecoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final EnderecoRepository enderecoRepo;
    private final EnderecoService enderecoService;

    public ClienteService(ClienteRepository clienteRepo, EnderecoRepository enderecoRepo, EnderecoService enderecoService) {
        this.clienteRepo = clienteRepo;
        this.enderecoRepo = enderecoRepo;
        this.enderecoService = enderecoService;
    }

    public Cliente criar(ClienteDTO dto) {
        var cepLimpo = dto.cep().replaceAll("\\D","");
        String cepFormatado = formata(cepLimpo);

        Endereco end = enderecoRepo.findByCep(cepFormatado)
                .orElseGet(() -> salvar(enderecoService.porCep(dto.cep())));

        Cliente cli = new Cliente();
        cli.setNome(dto.nome());
        cli.setEndereco(end);

        return clienteRepo.save(cli);
    }

    public Cliente buscar(Long id) {
        return clienteRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Endereco salvar(EnderecoDTO dto) {
        Endereco e = new Endereco(dto.cep(), dto.logradouro(), dto.bairro(), dto.cidade(), dto.uf());
        return enderecoRepo.save(e);
    }

    private String formata(String c){ return c.substring(0,5) + "-" + c.substring(5); }
}
