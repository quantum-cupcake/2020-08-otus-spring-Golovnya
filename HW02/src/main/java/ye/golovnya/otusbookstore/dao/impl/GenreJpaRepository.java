package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.GenreDao;
import ye.golovnya.otusbookstore.entities.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "SqlDialectInspection"})
@Repository
public class GenreJpaRepository implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public GenreJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(entityManager.find(Genre.class, id));
    }
}
