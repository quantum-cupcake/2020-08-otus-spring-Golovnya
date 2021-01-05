package ye.golovnya.otusbookstore.service;

import ye.golovnya.otusbookstore.entities.Book;

import java.util.List;

public interface BookService {

    Book getBook(Long id);

    List<Book> getBooksByTitle(String title);

    List<Book> getAllBooks();

    void createBook(String title, Long genreId, Long authorId);

    void updateBook(Long id, String title, Long authorId, Long genreId);

    void deleteBook(Long id);
}
