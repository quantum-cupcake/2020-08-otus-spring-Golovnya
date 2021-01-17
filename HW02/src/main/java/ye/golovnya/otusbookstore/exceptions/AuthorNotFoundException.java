package ye.golovnya.otusbookstore.exceptions;

public class AuthorNotFoundException extends NotFoundException {

    public AuthorNotFoundException(Long id) {
        super("author", id);
    }
}
