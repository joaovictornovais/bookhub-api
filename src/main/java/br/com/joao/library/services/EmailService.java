package br.com.joao.library.services;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.borrow.Borrow;
import br.com.joao.library.domain.email.Email;
import br.com.joao.library.domain.user.User;
import br.com.joao.library.util.emails.EmailBorrow;
import br.com.joao.library.util.emails.EmailReturn;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(Email email) {
        email.setSendDateEmail(LocalDateTime.now());
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true) ;
            helper.setFrom(email.getEmailFrom(), "BookHub");
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getText(), true);
            mailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Email createEmail(String userEmail, String subject) {
        return new Email(userEmail, subject);
    }

    public String editEmailBase(String emailBase, String firstName, String bookTitle, String bookCover) {
        emailBase = emailBase.replace("[[NOME]]", firstName);
        emailBase = emailBase.replace("[[TITULO]]", bookTitle);
        return emailBase.replace("[[CAPA]]", bookCover);
    }

    public void emailBorrowBook(User user, Book book, Borrow borrow) {
        String subject = "Informações: Empréstimo do Livro " + book.getTitle();
        Email email = createEmail(user.getEmail(), subject);

        String baseText = editEmailBase(
                EmailBorrow.borrow(),
                user.getFirstName(),
                book.getTitle(),
                book.getImgUrl());

        baseText = baseText.replace("[[DATAEMPRESTIMO]]",
                borrow.getBorrowedIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        baseText = baseText.replace("[[DATADEVOLVER]]",
                borrow.getDue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        email.setText(baseText);
        sendEmail(email);
    }

    public void emailReturnBook(User user, Book book, Borrow borrow) {
        String subject = "Confirmação de Devolução: " + book.getTitle();
        Email email = createEmail(user.getEmail(), subject);

        String baseText = editEmailBase(EmailReturn.returnBook(), user.getFirstName(), book.getTitle(), book.getImgUrl());

        baseText = baseText.replace("[[DATAEMPRESTIMO]]", borrow.getBorrowedIn().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        baseText = baseText.replace("[[DATADEVOLVER]]", borrow.getDue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        email.setText(baseText);
        sendEmail(email);
    }



}
