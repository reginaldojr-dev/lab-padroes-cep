package com.exemplo.lab.provider;

import com.exemplo.lab.dto.EnderecoDTO;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Order(99) // último na ordem de fallback
@Service
public class MockCepProvider implements CepProvider {


    private static final Map<String, EnderecoDTO> BASE = Map.of(
            "01310000", new EnderecoDTO("01310-000","Av. Paulista","Bela Vista","São Paulo","SP","mock"),
            "30140071", new EnderecoDTO("30140-071","Praça Sete de Setembro","Centro","Belo Horizonte","MG","mock")
    );

    @Override
    public Optional<EnderecoDTO> buscar(String cepSomenteDigitos) {
        return Optional.ofNullable(BASE.get(cepSomenteDigitos));
    }
}
