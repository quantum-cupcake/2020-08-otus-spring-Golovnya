package ye.golovnya.otusbookstore.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ye.golovnya.otusbookstore.entities.Author;
import ye.golovnya.otusbookstore.entities.Author_;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {

    private final static String PREFIX = "author_";
    private final static String ID_ALIAS = PREFIX + Author_.ID;
    private final static String NAME_ALIAS = PREFIX + Author_.NAME;

    @Override
    public Author mapRow(ResultSet resultSet, int i) throws SQLException {
        var author = new Author();
        author.setId(resultSet.getLong(ID_ALIAS));
        author.setName(resultSet.getString(NAME_ALIAS));
        return author;
    }
}
