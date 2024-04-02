package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.BookCategory;
import br.com.joao.library.domain.book.pk.BookCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCategoryRepository extends JpaRepository<BookCategory, BookCategoryPK> {
}
