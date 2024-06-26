package br.com.joao.library.domain.book;

import org.springframework.web.multipart.MultipartFile;

public record BookRequestDTO(
        String title,
        String author,
        String publisher,
        Integer pages,
        MultipartFile img
) {
}
