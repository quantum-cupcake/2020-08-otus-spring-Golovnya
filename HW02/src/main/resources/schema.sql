CREATE SCHEMA bookstore;

CREATE TABLE bookstore.author
(
    id   BIGINT PRIMARY KEY,
    name text NOT NULL
);

CREATE SEQUENCE author_id_seq;

CREATE TABLE bookstore.genre
(
    id   BIGINT PRIMARY KEY,
    name text NOT NULL
);

CREATE SEQUENCE genre_id_seq;

CREATE TABLE bookstore.book
(
    id       BIGINT PRIMARY KEY,
    title    text NOT NULL,
    genre_id BIGINT REFERENCES genre(id)
);

CREATE SEQUENCE book_id_seq;

CREATE TABLE bookstore.book_author
(
    book_id   BIGINT REFERENCES book (id),
    author_id BIGINT REFERENCES author (id),
    CONSTRAINT book_author_composite_pk PRIMARY KEY (book_id, author_id)
);
