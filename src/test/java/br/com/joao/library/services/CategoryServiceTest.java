package br.com.joao.library.services;

import br.com.joao.library.domain.book.Category;
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
        Category category = new Category(1L, "Mangá");
        categoryRepository.save(category);

        verify(categoryRepository, times(1)).save(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

    }

    @Test
    @DisplayName("Should throw a exception when category not found by ID")
    void findCategoryCase2() {
        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (categoryRepository.findById(1L).isEmpty())
                throw new EntityNotFoundException("Category with ID '" + 1L + "' not found");
        });

        Assertions.assertEquals("Category with ID '" + 1L + "' not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should create a category successfully")
    void createCategoryCase1() {
        String categoryName = "Mangá";
        Category category = new Category(1L, categoryName);

        if (categoryRepository.findCategoryByNameIgnoreCase(categoryName).isPresent()) {
            throw new InvalidArgumentsException("This category already exists");
        }

        categoryRepository.save(category);
        verify(categoryRepository, times(1)).save(category);
    }

    @Test
    @DisplayName("Should throw a exception when already exists a category with X name")
    void createCategoryCase2() {
        String categoryName = "Mangá";
        Category category = new Category(1L, categoryName);
        categoryRepository.save(category);

        verify(categoryRepository, times(1)).save(category);
        when(categoryRepository.findCategoryByNameIgnoreCase(categoryName)).thenReturn(Optional.of(category));

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
            if (categoryRepository.findCategoryByNameIgnoreCase(categoryName).isPresent()) {
                throw new InvalidArgumentsException("This category already exists");
            }
        });

        Assertions.assertEquals("This category already exists", thrown.getMessage());
    }
}