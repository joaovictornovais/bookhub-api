package br.com.joao.library.domain.book;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;

    @JsonBackReference
    @ManyToMany(mappedBy = "categories")
    private Set<Book> books = new HashSet<>();

    public Category(CategoryDTO categoryDTO) {
        BeanUtils.copyProperties(categoryDTO, this);
    }

}
