package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByNameIgnoreCase(String name);

}
