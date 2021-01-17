package ye.golovnya.otusbookstore.exceptions;

public class GenreNotFoundException extends NotFoundException {

    public GenreNotFoundException(Long id) {
        super("genre", id);
    }
}
