package com.exemplo.lab.dto;

import jakarta.validation.constraints.NotBlank;

public record ClienteDTO(
        @NotBlank String nome,
        @NotBlank String cep
) {}
