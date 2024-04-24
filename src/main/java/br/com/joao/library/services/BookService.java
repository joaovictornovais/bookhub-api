package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.repositories.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR: Book not found"));
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, Book bookDTO) {
        Book book = findBookById(id);
        BeanUtils.copyProperties(bookDTO, book);
        book.setId(id);
        return bookRepository.save(book);
    }

    public Book editBook(Long id, BookDTO bookDTO) {
        Book book = findBookById(id);
        BeanUtils.copyProperties(bookDTO, book);
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}
