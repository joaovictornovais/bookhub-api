package br.com.joao.library.services;

import br.com.joao.library.domain.book.Category;
import br.com.joao.library.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findCategory(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR: Category not found"));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

}
