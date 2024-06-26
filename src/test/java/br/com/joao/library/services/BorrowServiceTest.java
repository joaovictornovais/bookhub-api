package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.BorrowRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.*;

class BorrowServiceTest {

    @Mock
    BorrowRepository borrowRepository;
    @Mock
    UserService userService;
    @Mock
    BookService bookService;
    @Mock
    EmailService emailService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    @InjectMocks
    BorrowService borrowService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a borrow successfully")
    void borrowBookCase1() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        Book book = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 12 ", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/71S1+C-tbUL._SY425_.jpg");

        when(userService.findUser(user.getId())).thenReturn(user);
        when(bookService.findBookById(book.getId())).thenReturn(book);

        Borrow borrow = new Borrow();
        borrow.setDue(LocalDateTime.now().plusDays(30));
        borrow.setBorrowedTo(user);
        borrow.setBook(book);

        borrowRepository.save(borrow);
        verify(borrowRepository, times(1)).save(borrow);

        emailService.emailBorrowBook(user, book, borrow);
        verify(emailService, times(1)).emailBorrowBook(user, book, borrow);
    }

    @Test
    @DisplayName("Should throw a exception when Book already borrowed")
    void borrowBookCase2() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        Book book = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 12 ", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/71S1+C-tbUL._SY425_.jpg");

        when(bookService.findBookById(book.getId())).thenReturn(book);
        when(userService.findUser(user.getId())).thenReturn(user);

        Borrow borrow = new Borrow();
        borrow.setDue(LocalDateTime.now().plusDays(30));
        borrow.setBorrowedTo(user);
        borrow.setBook(book);

        borrowRepository.save(borrow);
        verify(borrowRepository, times(1)).save(borrow);

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
            if (borrowRepository.findBorrowByBook(book).isEmpty()) {
                throw new InvalidArgumentsException("Book already borrowed");
            }
        });

        Assertions.assertEquals("Book already borrowed", thrown.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when User already borrowed 3 books")
    void borrowBookCase3() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        Book book1 = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 12", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/71S1+C-tbUL._SY425_.jpg");
        Book book2 = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 11", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/61kC+ilwd3L._SY425_.jpg");
        Book book3 = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 3", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/517DFSH1+6L._SX342_SY445_.jpg");
        Book book4 = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 2", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/51sJMCzF6JS._SX342_SY445_.jpg");

        Borrow borrow1 = new Borrow(UUID.randomUUID(), user, book1);
        Borrow borrow2 = new Borrow(UUID.randomUUID(), user, book2);
        Borrow borrow3 = new Borrow(UUID.randomUUID(), user, book3);
        borrowRepository.save(borrow1);
        borrowRepository.save(borrow2);
        borrowRepository.save(borrow3);

        verify(borrowRepository, times(3)).save(any());

        Set<Borrow> borrows = new HashSet<>();
        borrows.add(borrow1);
        borrows.add(borrow2);
        borrows.add(borrow3);

        user.setBorrows(borrows);

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
            if (user.getBorrows().size() == 3)
                throw new InvalidArgumentsException("This user already borrowed 3 books");
        });

        Assertions.assertEquals("This user already borrowed 3 books", thrown.getMessage());

    }

    @Test
    @DisplayName("Should remove a borrow successfully")
    void returnBookCase1() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        Book book = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 12", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/71S1+C-tbUL._SY425_.jpg");

        Borrow borrow = new Borrow(UUID.randomUUID(), user, book);
        user.getBorrows().add(borrow);

        if (!borrow.getBorrowedTo().equals(user.getEmail()))
            throw new InvalidArgumentsException("This user dont borrowed this book");

        emailService.emailReturnBook(user, book, borrow);
        verify(emailService, times(1)).emailReturnBook(user, book, borrow);

        book.setBorrow(null);
        bookService.updateBook(book.getId(), book);
        borrowRepository.deleteById(borrow.getId());

        user.getBorrows().removeIf(id -> id.getId() == borrow.getId());

        verify(bookService, times(1)).updateBook(book.getId(), book);
        verify(borrowRepository, times(1)).deleteById(borrow.getId());
    }

    @Test
    @DisplayName("Should not remove a borrow when User dont borrowed this Book")
    void returnBookCase2() {
        User user = new User(UUID.randomUUID(), "João Victor", "Novais", "joaovkt.novais@gmail.com");
        Book book = new Book(
                UUID.randomUUID(), "Chainsaw Man Vol. 12", "Tatsuki Fujimoto", "Panini", 192,
                "https://m.media-amazon.com/images/I/71S1+C-tbUL._SY425_.jpg");

        Borrow borrow = new Borrow(UUID.randomUUID(), user, book);
        user.getBorrows().add(borrow);

        Exception thrown = Assertions.assertThrows(InvalidArgumentsException.class, () -> {
            if (!borrow.getBorrowedTo().equals("joao.dipaay@gmail.com"))
                throw new InvalidArgumentsException("This user dont borrowed this book");
        });

        Assertions.assertEquals("This user dont borrowed this book", thrown.getMessage());
    }
}