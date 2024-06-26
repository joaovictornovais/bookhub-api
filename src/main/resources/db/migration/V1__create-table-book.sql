CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE book(
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(100) NOT NULL,
    publisher VARCHAR(100) NOT NULL,
    pages INTEGER NOT NULL,
    img_url VARCHAR(255) NOT NULL
);