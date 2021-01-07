package ye.golovnya.otusbookstore.factory;

import ye.golovnya.otusbookstore.entities.Genre;

public class GenreFactory {

    private static final long DEFAULT_GENRE_ID = 1L;
    private static final String DEFAULT_GENRE_NAME = "Young Adult";

    public static Genre buildGenre() {
        return buildGenre(DEFAULT_GENRE_ID, DEFAULT_GENRE_NAME);
    }

    public static Genre buildGenre(long id, String name) {
        var genre = new Genre();
        genre.setId(id);
        genre.setName(name);
        return genre;
    }
}
