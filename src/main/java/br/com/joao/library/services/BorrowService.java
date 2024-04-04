package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowDTO;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.repositories.BorrowRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final UserService userService;
    private final BookService bookService;

    public BorrowService(BorrowRepository borrowRepository,
                         UserService userService,
                         BookService bookService) {
        this.borrowRepository = borrowRepository;
        this.userService = userService;
        this.bookService = bookService;
    }

    public Borrow borrowBook(BorrowDTO userId, Long bookId) {
        User user = userService.findUser(userId.userId());
        Book book = bookService.findBookById(bookId);

        if (findBorrowByBook(book) != null)
            throw new RuntimeException("Book already borrowed");

        if (user.getBorrows().size() == 3)
            throw new RuntimeException("This user already borrowed 3 books");

        Borrow borrow = new Borrow();
        borrow.setDue(LocalDateTime.now().plusDays(30));
        borrow.setBorrowedTo(user);
        borrow.setBook(book);
        return borrowRepository.save(borrow);
    }

    public Borrow findBorrowByBook(Book book) {
        return borrowRepository.findBorrowByBook(book);
    }

}
