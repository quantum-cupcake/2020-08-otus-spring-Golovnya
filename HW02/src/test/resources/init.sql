INSERT INTO bookstore.author(id, name)
VALUES (bookstore.author_id_seq.nextval, 'Jerome D. Salinger'),
       (bookstore.author_id_seq.nextval, 'Fyodor Dostoyevskiy');

INSERT INTO bookstore.genre(id, name)
VALUES (bookstore.genre_id_seq.nextval, 'Young Adult'),
       (bookstore.genre_id_seq.nextval, 'Thriller');

INSERT INTO bookstore.book(id, title, genre_id, author_id)
VALUES (bookstore.book_id_seq.nextval, 'The Laughing Man', 1, 1);

INSERT INTO bookstore.comment(id, value, book_id)
VALUES (bookstore.comment_id_seq.nextval, 'This is a comment', bookstore.book_id_seq.currval ),
       (bookstore.comment_id_seq.nextval, 'And this is another comment', bookstore.book_id_seq.currval )