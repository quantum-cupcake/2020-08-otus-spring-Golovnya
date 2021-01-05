package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.AuthorDao;
import ye.golovnya.otusbookstore.dao.mappers.AuthorMapper;
import ye.golovnya.otusbookstore.entities.Author;

import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
    private final RowMapper<Author> authorMapper;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.authorMapper = new AuthorMapper();
    }

    @Override
    public Author getById(long id) {
        Map<String, Long> params = Map.of("id", id);

        return namedParameterJdbcOperations.queryForObject("SELECT id author_id, name author_name FROM bookstore.author WHERE id = :id",
                params,
                authorMapper);
    }
}
