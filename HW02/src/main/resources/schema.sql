CREATE SCHEMA bookstore;

CREATE TABLE bookstore.author
(
    id   BIGINT PRIMARY KEY,
    name text NOT NULL
);

CREATE SEQUENCE bookstore.author_id_seq START WITH 1 INCREMENT BY 1 NOCYCLE;

CREATE TABLE bookstore.genre
(
    id   BIGINT PRIMARY KEY,
    name text NOT NULL
);

CREATE SEQUENCE bookstore.genre_id_seq START WITH 1 INCREMENT BY 50 NOCYCLE;

CREATE TABLE bookstore.book
(
    id       BIGINT PRIMARY KEY,
    title    text NOT NULL,
    genre_id BIGINT REFERENCES genre(id),
    author_id BIGINT REFERENCES author(id)
);

CREATE SEQUENCE bookstore.book_id_seq START WITH 1 INCREMENT BY 50 NOCYCLE;

CREATE TABLE bookstore.comment
(
    id       BIGINT PRIMARY KEY,
    value    text NOT NULL,
    book_id BIGINT REFERENCES book(id)
);

CREATE SEQUENCE bookstore.comment_id_seq START WITH 1 INCREMENT BY 50 NOCYCLE;