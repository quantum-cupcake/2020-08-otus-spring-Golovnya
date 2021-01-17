package ye.golovnya.otusbookstore.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ye.golovnya.otusbookstore.entities.Comment;
import ye.golovnya.otusbookstore.service.CommentService;

import java.util.Collection;
import java.util.stream.Collectors;

@ShellComponent
public class CommentCrudCommands {

    private final CommentService commentService;

    public CommentCrudCommands(CommentService commentService) {
        this.commentService = commentService;
    }

    @ShellMethod(value = "Post comment", key = {"c", "comment"})
    public String postComment(long bookId, String comment) {
        commentService.createComment(bookId, comment);
        return String.format("Опубликован комментарий к книге  %d: \"%s\"!", bookId, comment);
    }

    @ShellMethod(value = "Show comments", key = {"sc", "showComments"})
    public String showComments(long bookId) {
        var comments = commentService.getCommentsByBook(bookId);
        return commentsToString(comments);
    }

    @ShellMethod(value = "Update comment", key = {"uc", "updateComment"})
    public String updateComment(long commentId, String newValue) {
        commentService.updateComment(commentId, newValue);
        return String.format("Обновлен комментарий %d: %n\"%s\"!", commentId, newValue);
    }

    @ShellMethod(value = "Delete comment by id", key = {"dc", "deleteComment"})
    public String deleteComment(long commentId) {
        Comment comment = commentService.getComment(commentId);
        commentService.deleteComment(commentId);
        return String.format("Удален комментарий к книге %d: %n%s", comment.getBook().getId(), comment.getValue());
    }

    @ShellMethod(value = "Delete all comments to book", key = {"dac", "deleteAllComments"})
    public String deleteAllComment(long bookId) {
        commentService.deleteCommentsByBook(bookId);
        return String.format("Удалены все комментарии к книге %d!", bookId);
    }

    private String commentsToString(Collection<Comment> comments) {
        if (comments.isEmpty()) {
            return "Комментариев к книге не найдено!";
        } else {
            var output = comments.stream().map(Comment::toString).collect(Collectors.joining(System.lineSeparator()));
            return String.format("Комментарии: %n%s", output);
        }
    }
}
