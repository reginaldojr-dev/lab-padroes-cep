package com.exemplo.lab.dto;

public record EnderecoDTO(
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String uf,
        String source // quem respondeu: viacep, brasilapi, mock
) {}
