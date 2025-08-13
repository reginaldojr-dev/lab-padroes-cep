package com.exemplo.lab.provider;

import com.exemplo.lab.dto.EnderecoDTO;
import com.exemplo.lab.provider.feign.ViaCepClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Order(1) // primeira tentativa no fallback
@Service
public class ViaCepProvider implements CepProvider {

    private final ViaCepClient feign;

    public ViaCepProvider(ViaCepClient feign) {
        this.feign = feign;
    }

    @Override
    public Optional<EnderecoDTO> buscar(String c) {
        try {
            Map<String,Object> m = feign.buscar(c);
            if (Boolean.TRUE.equals(m.get("erro"))) return Optional.empty();

            return Optional.of(new EnderecoDTO(
                    formata(c),
                    (String)m.getOrDefault("logradouro",""),
                    (String)m.getOrDefault("bairro",""),
                    (String)m.getOrDefault("localidade",""),
                    (String)m.getOrDefault("uf",""),
                    "viacep"
            ));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private String formata(String c){ return c.substring(0,5) + "-" + c.substring(5); }
}
