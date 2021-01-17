package ye.golovnya.otusbookstore.exceptions;

public class CommentNotFoundException extends NotFoundException {

    public CommentNotFoundException(Long id) {
        super("comment", id);
    }
}
