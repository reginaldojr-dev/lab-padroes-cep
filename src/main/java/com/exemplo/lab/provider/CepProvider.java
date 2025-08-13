package com.exemplo.lab.provider;

import com.exemplo.lab.dto.EnderecoDTO;

import java.util.Optional;

public interface CepProvider {
    Optional<EnderecoDTO> buscar(String cepLimpo); // cep somente dígitos
}
