package com.exemplo.lab.service;

import com.exemplo.lab.dto.EnderecoDTO;
import com.exemplo.lab.provider.CepProvider;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final List<CepProvider> providers;

    public EnderecoService(List<CepProvider> providers) {
        this.providers = providers;
    }

    @Cacheable("enderecos")
    public EnderecoDTO porCep(String cep) {
        String c = cep.replaceAll("\\D","");
        Optional<EnderecoDTO> ok = providers.stream()
                .map(p -> p.buscar(c))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        return ok.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "CEP não encontrado"));
    }
}
