INSERT INTO bookstore.author(id, name)
VALUES (author_id_seq.nextval, 'Jerome D. Salinger'), (author_id_seq.nextval, 'Fyodor Dostoyevskiy');

INSERT INTO bookstore.genre(id, name)
VALUES (genre_id_seq.nextval, 'Young Adult'), (genre_id_seq.nextval, 'Thriller');

INSERT INTO bookstore.book(id, title, genre_id, author_id)
VALUES (book_id_seq.nextval, 'The Laughing Man', 1, 1);
