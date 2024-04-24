package br.com.joao.library.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BookDTO(
        @NotBlank(message = "Title should not be blank")
        String title,
        @NotBlank(message = "Author should not be blank")
        String author,
        @NotBlank(message = "Publisher should not be blank")
        String publisher,
        @NotNull(message = "Pages should no be null")
        Integer pages,
        @NotBlank(message = "Cover should not be blank")
        String cover) {
}
