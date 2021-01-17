package ye.golovnya.otusbookstore.factory;

import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.entities.Comment;

public class CommentFactory {

    private static final String DEFAULT_COMMENT_VALUE = "DEFAULT COMMENT";

    public static Comment buildComment(Book book) {
        return buildComment(DEFAULT_COMMENT_VALUE, book);
    }

    public static Comment buildComment(String value, Book book) {
        var comment = new Comment();
        comment.setValue(value);
        comment.setBook(book);
        return comment;
    }
}
