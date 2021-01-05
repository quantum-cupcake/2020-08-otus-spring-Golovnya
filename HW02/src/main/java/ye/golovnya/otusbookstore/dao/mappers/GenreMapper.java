package ye.golovnya.otusbookstore.dao.mappers;

import org.springframework.jdbc.core.RowMapper;
import ye.golovnya.otusbookstore.entities.Genre;
import ye.golovnya.otusbookstore.entities.Genre_;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {

    private final static String PREFIX = "genre_";
    private final static String ID_ALIAS = PREFIX + Genre_.ID;
    private final static String NAME_ALIAS = PREFIX + Genre_.NAME;

    @Override
    public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
        var genre = new Genre();
        genre.setId(resultSet.getLong(ID_ALIAS));
        genre.setName(resultSet.getString(NAME_ALIAS));

        return genre;
    }
}
