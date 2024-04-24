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

@Entity
@Table(name = "borrows")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        setBorrowedIn(LocalDateTime.now());
    }

    public Borrow(BorrowDTO borrowDTO) {
        BeanUtils.copyProperties(borrowDTO, this);
    }

    public String getBorrowedTo() {
        return borrowedTo.getEmail();
    }

}
