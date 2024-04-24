package br.com.joao.library.domain.book;

import jakarta.validation.constraints.NotBlank;

public record BookDTO(
        @NotBlank(message = "Title should not be blank")
        String title,
        @NotBlank(message = "Author should not be blank")
        String author,
        @NotBlank(message = "Publisher should not be blank")
        String publisher,
        @NotBlank(message = "Pages should not be blank")
        Integer pages,
        @NotBlank(message = "Cover should not be blank")
        String cover) {
}
