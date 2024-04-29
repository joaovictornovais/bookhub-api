package br.com.joao.library.controllers;

import br.com.joao.library.domain.book.Category;
import br.com.joao.library.domain.book.CategoryDTO;
import br.com.joao.library.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books/categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(description = "Salva uma nova categoria ao banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Já existe uma categoria com esse nome")
    })
    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CategoryDTO categoryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.createCategory(new Category(categoryDTO)));
    }

    @Operation(description = "Busca todas as categorias salvas no banco de dados")
    @ApiResponse(responseCode = "200", description = "Retorna todas as categorias")
    @GetMapping
    public ResponseEntity<List<Category>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @Operation(description = "Busca uma categoria com nome específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoria encontrada"),
            @ApiResponse(responseCode = "404", description = "Não foi encontrada uma categoria com o nome informado")
    })
    @GetMapping(params = "name")
    public ResponseEntity<Category> findCategoryByName(@RequestParam("name") String name) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findCategoryByName(name));
    }

    @Operation(description = "Deleta uma categoria do banco de dados")
    @ApiResponse(responseCode = "204", description = "Categoria deletada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
