package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.borrow.BorrowDTO;
import br.com.joao.library.domain.email.Email;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.repositories.BorrowRepository;
import br.com.joao.library.util.emails.EmailBorrow;
import br.com.joao.library.util.emails.EmailReturn;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    public Email createEmail(String userEmail, String subject) {
        return new Email(userEmail, subject);
    }

    public String editEmailBase(String emailBase, String firstName, String bookTitle, String bookCover) {
        emailBase = emailBase.replace("[[NOME]]", firstName);
        emailBase = emailBase.replace("[[TITULO]]", bookTitle);
        return emailBase.replace("[[CAPA]]", bookCover);
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

        emailBorrowBook(user, book, borrow);

        return borrowRepository.save(borrow);
    }

    public void emailBorrowBook(User user, Book book, Borrow borrow) {
        String subject = "Informações: Empréstimo do Livro " + book.getTitle();
        Email email = createEmail(user.getEmail(), subject);

        String baseText = editEmailBase(
                EmailBorrow.borrow(),
                user.getFirstName(),
                book.getTitle(),
                book.getCover());

        baseText = baseText.replace("[[DATAEMPRESTIMO]]",
                borrow.getBorrowedIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        baseText = baseText.replace("[[DATADEVOLVER]]",
                borrow.getDue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        email.setText(baseText);
        emailService.sendEmail(email);
    }

    public void returnBook(Long userId, Long bookId) {
        User user = userService.findUser(userId);
        Book book = bookService.findBookById(bookId);

        Borrow borrow = findBorrowByBook(book);

        if (borrow == null)
            throw new RuntimeException("This book aren't borrowed");

        if (!Objects.equals(borrow.getBorrowedTo(), user.getEmail()))
            throw new RuntimeException("This user dont borrowed this book");

        emailReturnBook(user, book, borrow);

        book.setBorrow(null);
        bookService.updateBook(book.getId(), book);
        borrowRepository.deleteById(borrow.getId());
    }

    public void emailReturnBook(User user, Book book, Borrow borrow) {
        String subject = "Confirmação de Devolução: " + book.getTitle();
        Email email = createEmail(user.getEmail(), subject);

        String baseText = editEmailBase(EmailReturn.returnBook(), user.getFirstName(), book.getTitle(), book.getCover());

        baseText = baseText.replace("[[DATAEMPRESTIMO]]", borrow.getBorrowedIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        baseText = baseText.replace("[[DATADEVOLVER]]", borrow.getDue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        email.setText(baseText);
        emailService.sendEmail(email);
    }

    public Borrow findBorrowByBook(Book book) {
        return borrowRepository.findBorrowByBook(book);
    }

}
