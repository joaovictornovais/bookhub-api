package br.com.joao.library.domain.borrow;

import br.com.joao.library.domain.book.Book;
import br.com.joao.library.domain.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "borrow")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private LocalDateTime borrowedIn;
    private LocalDateTime due;

    @ManyToOne
    @JoinColumn(name = "borrowed_to")
    private User borrowedTo;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Borrow() {
        this.borrowedIn = LocalDateTime.now();
    }

    public Borrow(BorrowDTO borrowDTO) {
        BeanUtils.copyProperties(borrowDTO, this);
    }

    public Borrow(UUID id, User user, Book book) {
        this.id = id;
        this.borrowedIn = LocalDateTime.now();
        this.due = LocalDateTime.now().plusDays(30);
        this.borrowedTo = user;
        this.book = book;
    }

    public String getBorrowedTo() {
        return borrowedTo.getEmail();
    }

}
