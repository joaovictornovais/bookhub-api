package br.com.joao.library.controllers;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookCategoryDTO;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowDTO;
import br.com.joao.library.services.BookCategoryService;
import br.com.joao.library.services.BookService;
import br.com.joao.library.services.BorrowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "*", maxAge = 3600)
public class BookController {

    private final BookService bookService;
    private final BookCategoryService bookCategoryService;
    private final BorrowService borrowService;

    public BookController(BookService bookService,
                          BookCategoryService bookCategoryService,
                          BorrowService borrowService) {
        this.bookService = bookService;
        this.bookCategoryService = bookCategoryService;
        this.borrowService = borrowService;
    }

    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(id));
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(new Book(bookDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.editBook(id, bookDTO));
    }

    @PostMapping("/{id}/categories")
    public ResponseEntity<Book> addCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(bookCategoryService.addCategory(id, bookCategoryDTO));
    }

    @DeleteMapping("/{id}/categories")
    public ResponseEntity<Book> removeCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        bookCategoryService.removeCategory(id, bookCategoryDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/borrow")
    public ResponseEntity<Borrow> borrowBook(@PathVariable Long id, @RequestBody BorrowDTO borrowDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.borrowBook(borrowDTO, id));
    }

    @DeleteMapping("/{id}/borrow")
    public ResponseEntity<Void> returnBook(@PathVariable Long id, @RequestParam("userId") Long userId) {
        borrowService.returnBook(userId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
