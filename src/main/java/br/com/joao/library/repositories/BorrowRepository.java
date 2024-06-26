package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, UUID> {

    Optional<Borrow> findBorrowByBook(Book book);

}
