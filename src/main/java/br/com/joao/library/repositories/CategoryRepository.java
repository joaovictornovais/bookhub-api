package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
