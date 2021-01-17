package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.AuthorDao;
import ye.golovnya.otusbookstore.entities.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
public class AuthorJpaRepository implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public AuthorJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(entityManager.find(Author.class, id));
    }
}
