package br.com.joao.library.domain.email;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@Entity
@Table(name = "emails")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;
    private String subject;
    private String text;
    private LocalDateTime sendDateEmail;
    private StatusEmail statusEmail;

    public Email(EmailDTO emailDTO) {
        BeanUtils.copyProperties(emailDTO, this);
    }

    public Email() {
        this.ownerRef = "BookHub - Livraria";
        this.emailFrom = System.getenv("MAIL_USERNAME");
    }

}
