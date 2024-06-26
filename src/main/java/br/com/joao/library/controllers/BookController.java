package br.com.joao.library.controllers;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookRequestDTO;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowRequestDTO;
import br.com.joao.library.domain.category.BookCategoryRequestDTO;
import br.com.joao.library.services.BookCategoryService;
import br.com.joao.library.services.BookService;
import br.com.joao.library.services.BorrowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findBookById(@PathVariable UUID bookId) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findBookById(bookId));
    }

    @Operation(description = "Salva livro no banco de dados")
    @ApiResponse(responseCode = "201", description = "Livro salvo no banco de dados")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Book> createBook(@RequestParam("title") String title,
                                           @RequestParam("author") String author,
                                           @RequestParam("publisher") String publisher,
                                           @RequestParam("pages") Integer pages,
                                           @RequestParam("image") MultipartFile image) {
        BookRequestDTO data = new BookRequestDTO(title, author, publisher, pages, image);
        return ResponseEntity.ok(bookService.createBook(data));
    }

    @Operation(description = "Edita informações de livro pré-existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro editado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Livro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios ficaram em branco ou nulos")
    })
    @PutMapping(value ="/{bookId}", consumes = "multipart/form-data")
    public ResponseEntity<Book> editBook(@PathVariable UUID bookId,
                                         @RequestParam("title") String title,
                                         @RequestParam("author") String author,
                                         @RequestParam("publisher") String publisher,
                                         @RequestParam("pages") Integer pages,
                                         @RequestParam("image") MultipartFile img
                                         ){
        BookRequestDTO data = new BookRequestDTO(title, author, publisher, pages, img);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.editBook(bookId, data));
    }

    @Operation(description = "Adiciona cateogria a um livro")
    @ApiResponse(responseCode = "200", description = "Categoria adicionada com sucesso")
    @PostMapping("/{bookId}/categories")
    public ResponseEntity<Book> addCategory(@PathVariable UUID bookId, @RequestBody BookCategoryRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(bookCategoryService.addCategory(bookId, data));
    }


    @Operation(description = "Desassocia uma categoria de um livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Categoria desassociada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Esse livro não possui essa categoria associada")
    })
    @DeleteMapping("/{bookId}/categories")
    public ResponseEntity<Book> removeCategory(@PathVariable UUID bookId, @RequestParam("categoryId") UUID categoryId) {
        bookCategoryService.removeCategory(bookId, categoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(description = "Realiza o empréstimo de um livro a um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Livro emprestado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Esse livro já foi emprestado a outro usuário")
    })
    @PostMapping("/{id}/borrow")
    public ResponseEntity<Borrow> borrowBook(@PathVariable UUID id, @RequestBody BorrowRequestDTO data) {
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.borrowBook(id, data));
    }

    @Operation(description = "Indica que o usuário devolveu o livro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Devolução realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "O livro não foi emprestado a esse usuário")
    })
    @DeleteMapping("/{bookId}/borrow")
    public ResponseEntity<Void> returnBook(@PathVariable UUID bookId, @RequestParam("userId") UUID userId) {
        borrowService.returnBook(bookId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
