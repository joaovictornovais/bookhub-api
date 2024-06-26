CREATE TABLE borrow(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    borrowed_in TIMESTAMP NOT NULL,
    due TIMESTAMP NOT NULL,

    borrowed_to UUID,
    FOREIGN KEY (borrowed_to) REFERENCES users(id),
    book_id UUID,
    FOREIGN KEY (book_id) REFERENCES book(id)
);