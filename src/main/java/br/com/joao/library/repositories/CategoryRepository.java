package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findCategoryByNameIgnoreCase(String name);

}
