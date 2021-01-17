package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.BookDao;
import ye.golovnya.otusbookstore.dao.mappers.BookMapper;
import ye.golovnya.otusbookstore.entities.Book;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final RowMapper<Book> bookMapper;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.bookMapper = new BookMapper();
    }

    @Override
    public int count() {
        return namedParameterJdbcOperations.getJdbcOperations()
                .queryForObject("SELECT count(*) FROM bookstore.book",
                        Integer.class
        );
    }

    @Override
    public void create(Book book) {
        Map<String, ? extends Serializable> params = Map.of(
                "title", book.getTitle(),
                "genre_id", book.getGenre().getId(),
                "author_id", book.getAuthor().getId());
        namedParameterJdbcOperations.update(
                "INSERT INTO bookstore.book(id, title, genre_id, author_id)" +
                        " VALUES (book_id_seq.nextval, :title, :genre_id, :author_id);",
                params
        );
    }

    @Override
    public void update(long id, Book changedBook) {
        Map<String, ? extends Serializable> params = Map.of(
                "id", id,
                "title", changedBook.getTitle(),
                "genre_id", changedBook.getGenre().getId(),
                "author_id", changedBook.getAuthor().getId());
        namedParameterJdbcOperations.update(
                "UPDATE bookstore.book" +
                        " SET title = :title, genre_id = :genre_id, author_id = :author_id" +
                        " WHERE id = :id",
                params
        );
    }

    @Override
    public Book getById(long id) {
        var params = Map.of(
                "id", id
        );
        return namedParameterJdbcOperations.queryForObject(
                "SELECT b.id book_id, b.title book_title, a.id author_id, a.name author_name, g.id genre_id, g.name genre_name" +
                        " FROM bookstore.book b join bookstore.genre g on b.genre_id = g.id join bookstore.author a on b.author_id = a.id" +
                        " WHERE b.id = :id",
                params,
                bookMapper
        );
    }

    @Override
    public List<Book> getByTitle(String title) {
        var params = Map.of(
                "title", "%" + title + "%"
        );
        return namedParameterJdbcOperations.query(
                "SELECT b.id book_id, b.title book_title, a.id author_id, a.name author_name, g.id genre_id, g.name genre_name" +
                        " FROM bookstore.book b join bookstore.genre g on b.genre_id = g.id join bookstore.author a on b.author_id = a.id" +
                        " WHERE b.title LIKE :title",
                params,
                bookMapper
        );
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.getJdbcOperations().query(
                "SELECT b.id book_id, b.title book_title, a.id author_id, a.name author_name, g.id genre_id, g.name genre_name" +
                        " FROM bookstore.book b join bookstore.genre g on b.genre_id = g.id join bookstore.author a on b.author_id = a.id",
                bookMapper
        );
    }

    @Override
    public void deleteById(long id) {
        Map<String, ? extends Serializable> params = Map.of(
                "id", id);
        namedParameterJdbcOperations.update("DELETE FROM bookstore.book WHERE id = :id", params);
    }
}
