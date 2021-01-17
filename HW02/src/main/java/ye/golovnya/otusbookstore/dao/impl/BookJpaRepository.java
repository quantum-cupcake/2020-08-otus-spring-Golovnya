package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.BookDao;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.exceptions.BookNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookJpaRepository implements BookDao {

    private static final String FETCHGRAPH = "javax.persistence.fetchgraph";

    @PersistenceContext
    private final EntityManager entityManager;

    public BookJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public int count() {
        TypedQuery<Integer> countQuery = entityManager.createQuery("SELECT count(b) from Book b", Integer.class);

        return countQuery.getSingleResult();
    }

    @Override
    public void create(Book book) {
        entityManager.persist(book);
    }

    @Override
    public void update(Book changedBook) {
        entityManager.merge(changedBook);
    }

    @Override
    public Optional<Book> getById(Long id) {
        TypedQuery<Book> bookTypedQuery = entityManager.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
        bookTypedQuery.setParameter("id", id);
        addCommentsGraph(bookTypedQuery);
        return getBookOptional(bookTypedQuery);
    }

    private Optional<Book> getBookOptional(TypedQuery<Book> query) {
        Optional<Book> result;
        try {
            result = Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException nre) {
            result = Optional.empty();
        }
        return result;
    }

    private void addCommentsGraph(TypedQuery<Book> query) {
        var commentsGraph = entityManager.getEntityGraph(Book.COMMENTS_GRAPH_NAME);
        query.setHint(FETCHGRAPH, commentsGraph);
    }

    @Override
    public List<Book> getByTitle(String title) {
        TypedQuery<Book> bookTypedQuery = entityManager.createQuery("SELECT b FROM Book b WHERE b.title LIKE :title", Book.class);
        bookTypedQuery.setParameter("title", "%" + title + "%");
        addCommentsGraph(bookTypedQuery);
        return bookTypedQuery.getResultList();
    }

    @Override
    public List<Book> getAll() {
        TypedQuery<Book> bookTypedQuery = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        addCommentsGraph(bookTypedQuery);
        return bookTypedQuery.getResultList();
    }

    @Override
    public void deleteById(long id) {
        entityManager.remove(getById(id).orElseThrow(() -> new BookNotFoundException(id)));
    }
}
