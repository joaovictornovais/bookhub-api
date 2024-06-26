package br.com.joao.library.services;

import br.com.joao.library.domain.book.Category;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategory(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Category with ID '" + id + "' not found"));
    }

    public Category createCategory(Category category) {
        try {
            findCategoryByName(category.getName());
            throw new InvalidArgumentsException("This category already exists");
        } catch (EntityNotFoundException e) {
            return categoryRepository.save(category);
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findCategoryByName(String name) {
        return categoryRepository.findCategoryByNameIgnoreCase(name).orElseThrow(()
                -> new EntityNotFoundException("Category called '" + name + "' not found"));
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }

}
