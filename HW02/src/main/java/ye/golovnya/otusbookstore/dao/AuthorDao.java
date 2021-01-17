package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Author;

import java.util.Optional;

public interface AuthorDao {

    Optional<Author> getById(long id);
}
