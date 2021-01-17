package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentDao {

    void create(Comment comment);

    void update(Comment changedComment);

    Optional<Comment> getById(Long id);

    List<Comment> getByBookId(Long bookId);

    void deleteById(long id);

    void deleteByBookId(long bookId);
}
