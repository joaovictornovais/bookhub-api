package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowDTO;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.exceptions.InvalidArgumentsException;
import br.com.joao.library.repositories.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserService userService;
    private final BookService bookService;
    private final EmailService emailService;

    public BorrowService(BorrowRepository borrowRepository,
                         UserService userService,
                         BookService bookService,
                         EmailService emailService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.bookService = bookService;
        this.emailService = emailService;
    }

    public Borrow borrowBook(BorrowDTO userId, Long bookId) {
        User user = userService.findUser(userId.userId());
        Book book = bookService.findBookById(bookId);

        if (findBorrowByBook(book) != null)
            throw new InvalidArgumentsException("Book already borrowed");

        if (user.getBorrows().size() == 3)
            throw new InvalidArgumentsException("This user already borrowed 3 books");

        Borrow borrow = new Borrow();
        borrow.setDue(LocalDateTime.now().plusDays(30));
        borrow.setBorrowedTo(user);
        borrow.setBook(book);

        emailService.emailBorrowBook(user, book, borrow);

        return borrowRepository.save(borrow);
    }

    public void returnBook(Long userId, Long bookId) {
        User user = userService.findUser(userId);
        Book book = bookService.findBookById(bookId);

        Borrow borrow = findBorrowByBook(book);

        if (!Objects.equals(borrow.getBorrowedTo(), user.getEmail()))
            throw new InvalidArgumentsException("This user dont borrowed this book");

        emailService.emailReturnBook(user, book, borrow);

        book.setBorrow(null);
        bookService.updateBook(book.getId(), book);
        borrowRepository.deleteById(borrow.getId());
    }

    public Borrow findBorrowByBook(Book book) {
        return borrowRepository.findBorrowByBook(book).orElse(null);
    }

}
