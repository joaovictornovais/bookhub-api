package br.com.joao.library.domain.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(
        @NotBlank(message = "Name should not be blank")
        String name) {
}
