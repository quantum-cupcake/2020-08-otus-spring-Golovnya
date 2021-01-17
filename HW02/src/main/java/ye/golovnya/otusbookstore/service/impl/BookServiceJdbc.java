package ye.golovnya.otusbookstore.service.impl;

import org.springframework.stereotype.Service;
import ye.golovnya.otusbookstore.dao.AuthorDao;
import ye.golovnya.otusbookstore.dao.BookDao;
import ye.golovnya.otusbookstore.dao.GenreDao;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.service.BookService;

import java.util.List;

@Service
public class BookServiceJdbc implements BookService {

    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    public BookServiceJdbc(BookDao bookDao, GenreDao genreDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
        this.authorDao = authorDao;
    }

    @Override
    public Book getBook(Long id) {
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public void createBook(String title, Long genreId, Long authorId) {
        var book = buildBook(title, genreId, authorId);
        bookDao.create(book);
    }

    @Override
    public void updateBook(Long id, String title, Long authorId, Long genreId) {
        var book = buildBook(title, genreId, authorId);
        bookDao.update(id, book);
    }

    @Override
    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }

    private Book buildBook(String title, Long genreId, Long authorId) {
        var book = new Book();
        book.setTitle(title);
        book.setGenre(genreDao.getById(genreId));
        book.setAuthor(authorDao.getById(authorId));
        return book;
    }
}
