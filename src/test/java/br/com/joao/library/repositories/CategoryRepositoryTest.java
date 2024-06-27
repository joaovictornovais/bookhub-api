package br.com.joao.library.repositories;

import br.com.joao.library.domain.category.Category;
import br.com.joao.library.domain.category.CategoryRequestDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class CategoryRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should get a category from DB")
    void findCategoryByNameCase1() {
        String categoryName = "Mangá";
        CategoryRequestDTO data = new CategoryRequestDTO(categoryName);
        this.createCategory(data);

        Optional<Category> result = categoryRepository.findCategoryByNameIgnoreCase(categoryName);
        
        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get a category from DB")
    void findCategoryByNameCase2() {
        String categoryName = "Mangá";
        Optional<Category> result = categoryRepository.findCategoryByNameIgnoreCase(categoryName);

        assertThat(result.isEmpty()).isTrue();
    }

    private Category createCategory(CategoryRequestDTO data) {
        Category newCategory = new Category(data);
        this.entityManager.persist(newCategory);
        return newCategory;
    }

}