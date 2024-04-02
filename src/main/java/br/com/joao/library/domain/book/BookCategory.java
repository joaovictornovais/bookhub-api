package br.com.joao.library.domain.book;

import br.com.joao.library.domain.book.pk.BookCategoryPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_category")
@NoArgsConstructor
public class BookCategory {

    @EmbeddedId
    private final BookCategoryPK id = new BookCategoryPK();

    public BookCategory(Book book, Category category) {
        setBook(book);
        setCategory(category);
    }

    public BookCategoryPK getId() {
        return id;
    }

    public void setCategory(Category category) {
        id.setCategory(category);
    }

    public void setBook(Book book) {
        id.setBook(book);
    }

    public Category getCategory() {
        return id.getCategory();
    }

    public Book getBook() {
        return id.getBook();
    }



}
