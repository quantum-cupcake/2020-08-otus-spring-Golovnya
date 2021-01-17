package ye.golovnya.otusbookstore.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ye.golovnya.otusbookstore.entities.Author;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.entities.Book_;
import ye.golovnya.otusbookstore.entities.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    private final static String PREFIX = "book_";
    private final static String ID_ALIAS = PREFIX + Book_.ID;
    private final static String TITLE_ALIAS = PREFIX + Book_.TITLE;

    private final RowMapper<Genre> genreMapper;
    private final RowMapper<Author> authorMapper;

    public BookMapper() {
        this.genreMapper = new GenreMapper();
        this.authorMapper = new AuthorMapper();
    }

    @Override
    public Book mapRow(ResultSet resultSet, int i) throws SQLException {
        var book = new Book();
        book.setId(resultSet.getLong(ID_ALIAS));
        book.setTitle(resultSet.getString(TITLE_ALIAS));

        book.setGenre(genreMapper.mapRow(resultSet, i));
        book.setAuthor(authorMapper.mapRow(resultSet, i));

        return book;
    }
}
