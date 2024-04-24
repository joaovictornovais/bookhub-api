package br.com.joao.library.repositories;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.book.BookDTO;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.domain.user.UserDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@ActiveProfiles("test")
class BorrowRepositoryTest {

    @Autowired
    BorrowRepository borrowRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get a Borrow successfully from DB")
    void findBorrowByBookCase1() {
        UserDTO userDTO = new UserDTO(
                "João Victor",
                "Novais",
                "joaovkt.novais@gmail.com"
        );

        BookDTO bookDTO = new BookDTO(
                "Jujutsu Kaisen: Batalha de Feiticeiros Vol. 25",
                "Gege Akutami",
                "Panini",
                208,
                "https://m.media-amazon.com/images/I/61bm6nkT1GL._SY425_.jpg"
        );

        User user = this.createUser(userDTO);
        Book book = this.createBook(bookDTO);
        this.createBorrow(user, book);

        Optional<Borrow> result = this.borrowRepository.findBorrowByBook(book);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Should not get a Borrow from DB when relation Book/User not exists")
    void findBorrowByBookCase2() {
        BookDTO bookDTO = new BookDTO(
                "Jujutsu Kaisen: Batalha de Feiticeiros Vol. 25",
                "Gege Akutami",
                "Panini",
                208,
                "https://m.media-amazon.com/images/I/61bm6nkT1GL._SY425_.jpg"
        );

        Book book = this.createBook(bookDTO);

        Optional<Borrow> result = this.borrowRepository.findBorrowByBook(book);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserDTO data) {
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }

    private Book createBook(BookDTO data) {
        Book newBook = new Book(data);
        this.entityManager.persist(newBook);
        return newBook;
    }

    private Borrow createBorrow(User user, Book book) {
        Borrow borrow = new Borrow();
        borrow.setDue(LocalDateTime.now().plusDays(30));
        borrow.setBorrowedTo(user);
        borrow.setBook(book);
        this.entityManager.persist(borrow);
        return borrow;
    }
}