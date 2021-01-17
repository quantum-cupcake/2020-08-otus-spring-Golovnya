package ye.golovnya.otusbookstore.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ye.golovnya.otusbookstore.dao.BookDao;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.exceptions.BookNotFoundException;
import ye.golovnya.otusbookstore.service.AuthorService;
import ye.golovnya.otusbookstore.service.BookService;
import ye.golovnya.otusbookstore.service.GenreService;

import java.util.List;

@Service
public class BookServiceJdbc implements BookService {

    private final BookDao bookDao;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookServiceJdbc(BookDao bookDao, GenreService genreService, AuthorService authorService) {
        this.bookDao = bookDao;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @Override
    public Book getBook(Long id) {
        return getBookByIdOrThrow(id);
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
    @Transactional
    public void createBook(String title, Long genreId, Long authorId) {
        var book = buildBook(title, genreId, authorId);
        bookDao.create(book);
    }

    @Override
    @Transactional
    public void updateBook(Long id, String title, Long authorId, Long genreId) {
        var book = getBookByIdOrThrow(id);
        book.setTitle(title);
        if (!book.getAuthor().getId().equals(authorId)) {
            book.setAuthor(authorService.getAuthor(authorId));
        }
        if (!book.getGenre().getId().equals(genreId)) {
            book.setGenre(genreService.getGenre(genreId));
        }
        bookDao.update(book);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookDao.deleteById(id);
    }

    private Book buildBook(String title, Long genreId, Long authorId) {
        var book = new Book();
        book.setTitle(title);
        book.setGenre(genreService.getGenre(genreId));
        book.setAuthor(authorService.getAuthor(authorId));
        return book;
    }

    private Book getBookByIdOrThrow(Long id) {
        return bookDao.getById(id).orElseThrow(
                () -> new BookNotFoundException(id)
        );
    }
}
