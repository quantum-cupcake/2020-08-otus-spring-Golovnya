package ye.golovnya.otusbookstore.factory;

import ye.golovnya.otusbookstore.entities.Author;

public class AuthorFactory {

    private static final long DEFAULT_AUTHOR_ID = 1L;
    private static final String DEFAULT_AUTHOR_NAME = "Jerome D. Salinger";

    public static Author buildAuthor() {
        return buildAuthor(DEFAULT_AUTHOR_ID, DEFAULT_AUTHOR_NAME);
    }

    public static Author buildAuthor(long id, String name) {
        var author = new Author();
        author.setId(id);
        author.setName(name);
        return author;
    }
}
