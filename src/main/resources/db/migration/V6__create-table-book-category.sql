CREATE TABLE book_category(
    book_id UUID,
    FOREIGN KEY (book_id) REFERENCES book(id),
    category_id UUID,
    FOREIGN KEY (category_id) REFERENCES category(id)
);