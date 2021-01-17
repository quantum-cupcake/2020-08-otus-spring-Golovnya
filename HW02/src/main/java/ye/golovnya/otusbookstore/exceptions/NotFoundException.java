package ye.golovnya.otusbookstore.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String description) {
        super(String.format("Requested %s not found", description));
    }

    public NotFoundException(String description, Long id) {
        super(String.format("Requested %s with id %d not found", description, id));
    }
}
