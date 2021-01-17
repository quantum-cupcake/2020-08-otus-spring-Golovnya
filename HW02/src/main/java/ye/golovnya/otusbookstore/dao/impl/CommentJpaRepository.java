package ye.golovnya.otusbookstore.dao.impl;

import org.springframework.stereotype.Repository;
import ye.golovnya.otusbookstore.dao.CommentDao;
import ye.golovnya.otusbookstore.entities.Comment;
import ye.golovnya.otusbookstore.exceptions.CommentNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class CommentJpaRepository implements CommentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    public CommentJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Override
    public void create(Comment comment) {
        entityManager.persist(comment);
    }

    @Override
    public void update(Comment changedComment) {
        entityManager.merge(changedComment);
    }

    @Override
    public Optional<Comment> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public void deleteById(long id) {
        var comment = getById(id);
        entityManager.remove(comment.orElseThrow(() -> new CommentNotFoundException(id)));
    }

    @Override
    public List<Comment> getByBookId(Long bookId) {
        TypedQuery<Comment> query = entityManager.createQuery("SELECT c FROM Comment c where c.book.id = :bookId", Comment.class);
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public void deleteByBookId(long bookId) {
        Query query = entityManager.createQuery("DELETE FROM Comment c where c.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }
}
