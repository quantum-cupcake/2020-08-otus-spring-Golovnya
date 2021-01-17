package ye.golovnya.otusbookstore.exceptions;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException(String title) {
        super(String.format("book titled %s", title));
    }

    public BookNotFoundException(Long id) {
        super("book", id);
    }
}
