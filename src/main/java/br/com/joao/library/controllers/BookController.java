package br.com.joao.library.controllers;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookCategoryDTO;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowDTO;
import br.com.joao.library.services.BookCategoryService;
import br.com.joao.library.services.BookService;
import br.com.joao.library.services.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(description = "Buscar todos os livros")
    @ApiResponse(responseCode = "200", description = "Retorna os livros")
    @GetMapping
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @Operation(description = "Busca o livro por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o livro"),
            @ApiResponse(responseCode = "404", description = "Não existe livro com o ID informado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(id));
    }

    @Operation(description = "Salva livro no banco de dados")
    @ApiResponse(responseCode = "201", description = "Livro salvo no banco de dados")
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(new Book(bookDTO)));
    }

    @Operation(description = "Edita informações de livro pré-existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro editado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios ficaram em branco ou nulos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.editBook(id, bookDTO));
    }

    @Operation(description = "Adiciona cateogria a um livro")
    @ApiResponse(responseCode = "200", description = "Categoria adicionada com sucesso")
    @PostMapping("/{id}/categories")
    public ResponseEntity<Book> addCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(bookCategoryService.addCategory(id, bookCategoryDTO));
    }


    @Operation(description = "Desassocia uma categoria de um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria desassociada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Esse livro não possui essa categoria associada")
    })
    @DeleteMapping("/{id}/categories")
    public ResponseEntity<Book> removeCategory(@PathVariable Long id, @RequestBody BookCategoryDTO bookCategoryDTO) {
        bookCategoryService.removeCategory(id, bookCategoryDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(description = "Realiza o empréstimo de um livro a um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro emprestado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Esse livro já foi emprestado a outro usuário")
    })
    @PostMapping("/{id}/borrow")
    public ResponseEntity<Borrow> borrowBook(@PathVariable Long id, @RequestBody BorrowDTO borrowDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.borrowBook(borrowDTO, id));
    }

    @Operation(description = "Indica que o usuário devolveu o livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolução realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "O livro não foi emprestado a esse usuário")
    })
    @DeleteMapping("/{id}/borrow")
    public ResponseEntity<Void> returnBook(@PathVariable Long id, @RequestParam("userId") Long userId) {
        borrowService.returnBook(userId, id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
