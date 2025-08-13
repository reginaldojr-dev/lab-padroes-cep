package com.exemplo.lab.provider;

import com.exemplo.lab.dto.EnderecoDTO;
import com.exemplo.lab.provider.feign.BrasilApiClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Order(2) // segunda tentativa no fallback
@Service
public class BrasilApiProvider implements CepProvider {

    private final BrasilApiClient feign;

    public BrasilApiProvider(BrasilApiClient feign) {
        this.feign = feign;
    }

    @Override
    public Optional<EnderecoDTO> buscar(String c) {
        try {
            Map<String,Object> m = feign.buscar(c);
            return Optional.of(new EnderecoDTO(
                    (String)m.getOrDefault("cep", formata(c)),
                    (String)m.getOrDefault("street",""),
                    (String)m.getOrDefault("neighborhood",""),
                    (String)m.getOrDefault("city",""),
                    (String)m.getOrDefault("state",""),
                    "brasilapi"
            ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String formata(String c){ return c.substring(0,5) + "-" + c.substring(5); }
}
