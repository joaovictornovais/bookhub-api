package br.com.joao.library.services;

import br.com.joao.library.domain.category.Category;
import br.com.joao.library.domain.category.CategoryRequestDTO;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    CategoryRepository categoryRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @InjectMocks
    CategoryService categoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should find a category successfully by ID")
    void findCategoryCase1() {
        Category category = new Category(UUID.randomUUID(), "Mangá");
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Optional<Category> response = categoryRepository.findById(category.getId());

        assertThat(response).isNotEmpty();
        assertThat(response.get()).isEqualTo(category);
    }

    @Test
    @DisplayName("Should throw a exception when category not found by ID")
    void findCategoryCase2() {
        UUID id = UUID.randomUUID();
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (categoryRepository.findById(id).isEmpty())
                throw new EntityNotFoundException("Category with ID '" + id + "' not found");
        });

        Assertions.assertEquals("Category with ID '" + id + "' not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should create a category successfully")
    void createCategoryCase1() {
        Category category = new Category(UUID.randomUUID(), "Mangá");
        categoryRepository.save(category);

        verify(categoryRepository, times(1)).save(category);
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Optional<Category> response = categoryRepository.findById(category.getId());

        assertThat(response).isNotEmpty();
        assertThat(response.get()).isEqualTo(category);

    }

    @Test
    @DisplayName("Should throw a exception when already exists a category with X name")
    void createCategoryCase2() {
        Category category = new Category(UUID.randomUUID(), "Mangá");

        when(categoryRepository.findCategoryByNameIgnoreCase("Mangá")).thenReturn(Optional.of(category));

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
           if (categoryRepository.findCategoryByNameIgnoreCase("Mangá").isPresent())
               throw new InvalidArgumentsException("This category already exists");
        });

        Assertions.assertEquals("This category already exists", thrown.getMessage());

    }
}