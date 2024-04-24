package br.com.joao.library.domain.book;

import jakarta.validation.constraints.NotBlank;

public record CategoryDTO(
        @NotBlank(message = "Name should not be blank")
        String name) {
}
