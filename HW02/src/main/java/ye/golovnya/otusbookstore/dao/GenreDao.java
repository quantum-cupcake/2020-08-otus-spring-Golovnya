package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> getById(long id);
}
