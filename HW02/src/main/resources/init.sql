INSERT INTO bookstore.author(id, name)
VALUES (bookstore.author_id_seq.nextval, 'Stephen King');

INSERT INTO bookstore.genre(id, name)
VALUES (bookstore.genre_id_seq.nextval, 'Thriller');

INSERT INTO bookstore.book(id, title, genre_id, author_id)
VALUES (bookstore.book_id_seq.nextval, 'The Stand', bookstore.genre_id_seq.currval, bookstore.author_id_seq.currval);
