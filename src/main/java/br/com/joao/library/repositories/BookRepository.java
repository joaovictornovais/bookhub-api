package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
