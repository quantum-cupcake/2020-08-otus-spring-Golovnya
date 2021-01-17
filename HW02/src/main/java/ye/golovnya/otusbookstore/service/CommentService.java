package ye.golovnya.otusbookstore.service;

import ye.golovnya.otusbookstore.entities.Comment;

import java.util.List;

public interface CommentService {

    Comment getComment(Long id);

    List<Comment> getCommentsByBook(Long bookId);

    void createComment(Long bookId, String value);

    void updateComment(Long id, String newValue);

    void deleteComment(Long id);

    void deleteCommentsByBook(Long bookId);
}
