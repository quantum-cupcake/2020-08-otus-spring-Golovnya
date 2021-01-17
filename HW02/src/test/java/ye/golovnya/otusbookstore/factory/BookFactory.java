package ye.golovnya.otusbookstore.factory;

import ye.golovnya.otusbookstore.entities.Author;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.entities.Genre;

public class BookFactory {

    private static final String DEFAULT_BOOK_TITLE = "The Catcher In The Rye";

    public static Book buildBook() {
        return buildBook(DEFAULT_BOOK_TITLE, GenreFactory.buildGenre(), AuthorFactory.buildAuthor());
    }

    public static Book buildBook(String title, Genre genre, Author author) {
        var book = new Book();
        book.setTitle(title);
        book.setGenre(genre);
        book.setAuthor(author);
        return book;
    }
}
