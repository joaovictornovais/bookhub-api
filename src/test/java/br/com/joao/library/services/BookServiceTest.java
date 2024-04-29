package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.exceptions.EntityNotFoundException;
import br.com.joao.library.repositories.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @InjectMocks
    BookService bookService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a Book successfully")
    void createBookCase1() {
        Book book = new Book(1L, "Livro 1", "John Doe", "Editora Xis", 192, "cover.jpg");
        bookRepository.save(book);

        verify(bookRepository, times(1)).save(book);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> response = bookRepository.findById(1L);

        assertThat(response).isPresent();
        assertThat(response.get()).isEqualTo(book);

    }

    @Test
    @DisplayName("Should find a book by ID successfully")
    void findBookByIdCase1() {
        Book book = new Book(1L, "Livro 1", "John Doe", "Editora Xis", 192, "cover.jpg");

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> response = bookRepository.findById(1L);

        assertThat(response).isPresent();
        assertThat(response.get()).isEqualTo(book);

    }

    @Test
    @DisplayName("Should throw a exception when not found a book by ID")
    void findBookByIdCase2() {
        Optional<Book> response = bookRepository.findById(1L);

        Exception thrown = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            if (bookRepository.findById(1L).isEmpty())
                throw new EntityNotFoundException("Book not found");
        });

        Assertions.assertEquals("Book not found", thrown.getMessage());
    }

    @Test
    @DisplayName("Should find all Books sucessfully")
    void findAllBooksCase1() {
        Book book1 = new Book(1L, "Livro 1", "John Doe", "Editora X",
                192, "cover.jpg");
        Book book2 = new Book(2L, "Livro 2", "John Doe", "Editora Y", 180,
                "cover-2.jpg");

        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<Book> books = bookRepository.findAll();

        assertThat(books).isNotEmpty();
        assertThat(books).contains(book1, book2);
    }

    @Test
    @DisplayName("Should edit a book sucessfully")
    void editBookCase1() {
        Book book = new Book(1L, "Livro 1", "John Doe", "Editora X",
                192, "cover.jpg");

        BookDTO data = new BookDTO("Livro 1 - O retorno", "John Doe",
                "Editora Xis", 192, "cover-retorno.jpg");

        BeanUtils.copyProperties(data, book);
        bookRepository.save(book);
        verify(bookRepository, times(1)).save(book);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> response = bookRepository.findById(1L);

        assertThat(response).isPresent();
        assertThat(response.get().getTitle()).isEqualTo(data.title());

    }

}