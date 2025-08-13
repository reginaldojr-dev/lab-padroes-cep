package com.exemplo.lab.provider.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@FeignClient(name = "brasilapi", url = "https://brasilapi.com.br/api/cep/v2")
public interface BrasilApiClient {
    @GetMapping("/{cep}")
    Map<String, Object> buscar(@PathVariable("cep") String cep);
}
