package br.com.joao.library.domain.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class Email {

    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;

    public Email() {
        this.ownerRef = "BookHub - Livraria";
        this.emailFrom = System.getenv("MAIL_USERNAME");
    }

    public Email(String userEmail, String subject) {
        this.ownerRef = "BookHub - Livraria";
        this.emailFrom = System.getenv("MAIL_USERNAME");
        this.emailTo = userEmail;
        this.subject = subject;
    }

}
