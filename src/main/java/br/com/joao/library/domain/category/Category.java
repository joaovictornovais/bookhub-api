package br.com.joao.library.domain.category;

import br.com.joao.library.domain.book.Book;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();

    public Category(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category(CategoryRequestDTO categoryRequestDTO) {
        BeanUtils.copyProperties(categoryRequestDTO, this);
    }

}
