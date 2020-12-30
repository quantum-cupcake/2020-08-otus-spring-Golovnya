INSERT INTO bookstore.author(id, name)
VALUES (author_id_seq.nextval, 'Stephen King');

INSERT INTO bookstore.genre(id, name)
VALUES (genre_id_seq.nextval, 'Thriller');

INSERT INTO bookstore.book(id, title, genre_id)
VALUES (book_id_seq.nextval, 'The Stand', genre_id_seq.currval);

INSERT INTO bookstore.book_author(author_id, book_id)
VALUES (author_id_seq.currval, book_id_seq.currval);