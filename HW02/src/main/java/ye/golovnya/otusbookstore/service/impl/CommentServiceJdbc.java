package ye.golovnya.otusbookstore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ye.golovnya.otusbookstore.dao.CommentDao;
import ye.golovnya.otusbookstore.entities.Comment;
import ye.golovnya.otusbookstore.exceptions.CommentNotFoundException;
import ye.golovnya.otusbookstore.service.BookService;
import ye.golovnya.otusbookstore.service.CommentService;

import java.util.List;

@Service
public class CommentServiceJdbc implements CommentService {

    private final CommentDao commentDao;
    private final BookService bookService;

    public CommentServiceJdbc(CommentDao commentDao, BookService bookService) {
        this.commentDao = commentDao;
        this.bookService = bookService;
    }

    @Override
    public Comment getComment(Long id) {
        return getCommentByIdOrThrow(id);
    }

    @Override
    public List<Comment> getCommentsByBook(Long bookId) {
        bookService.getBook(bookId); //убедимся, что книга существует
        return commentDao.getByBookId(bookId);
    }

    @Override
    @Transactional
    public void createComment(Long bookId, String value) {
        var book = bookService.getBook(bookId);
        var comment = new Comment();
        comment.setBook(book);
        comment.setValue(value);
        commentDao.create(comment);
    }

    @Override
    @Transactional
    public void updateComment(Long id, String newValue) {
        var comment = getCommentByIdOrThrow(id);
        comment.setValue(newValue);
        commentDao.update(comment);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCommentsByBook(Long bookId) {
        bookService.getBook(bookId); //убедимся, что книга существует
        commentDao.deleteByBookId(bookId);
    }

    private Comment getCommentByIdOrThrow(Long id) {
        return commentDao.getById(id).orElseThrow(
                () -> new CommentNotFoundException(id)
        );
    }
}
