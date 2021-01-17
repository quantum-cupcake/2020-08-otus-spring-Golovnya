package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.GenreDao;
import ye.golovnya.otusbookstore.dao.mappers.GenreMapper;
import ye.golovnya.otusbookstore.entities.Genre;

import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final RowMapper<Genre> genreMapper;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.genreMapper = new GenreMapper();
    }

    @Override
    public Genre getById(long id) {
        Map<String, Long> params = Map.of("id", id);

        return namedParameterJdbcOperations.queryForObject("SELECT id genre_id, name genre_name FROM bookstore.genre WHERE id = :id",
                params,
                genreMapper);
    }
}
