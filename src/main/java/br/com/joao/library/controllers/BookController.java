package br.com.joao.library.controllers;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookCategory;
import br.com.joao.library.domain.book.BookCategoryDTO;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.services.BookCategoryService;
import br.com.joao.library.services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookCategoryService bookCategoryService;

    public BookController(BookService bookService, BookCategoryService bookCategoryService) {
        this.bookService = bookService;
        this.bookCategoryService = bookCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.createBook(new Book(bookDTO)));
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<Book> addCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        return ResponseEntity.ok(bookCategoryService.addCategory(id, bookCategoryDTO));
    }

    @DeleteMapping("/{id}/categories")
    public ResponseEntity<Book> removeCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        bookCategoryService.removeCategory(id, bookCategoryDTO);
        return ResponseEntity.noContent().build();
    }

}
