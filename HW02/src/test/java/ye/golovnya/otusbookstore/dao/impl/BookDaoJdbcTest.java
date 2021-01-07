package ye.golovnya.otusbookstore.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ye.golovnya.otusbookstore.OtusBookstoreApplication;
import ye.golovnya.otusbookstore.entities.Book;
import ye.golovnya.otusbookstore.factory.BookFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Import(BookDaoJdbc.class)
@JdbcTest
class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenCountReturns() {
        int count = bookDaoJdbc.count();
        assertEquals(1, count);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenGetByIdReturnsBook() {
        var book = bookDaoJdbc.getById(1L);
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenGetByTitleReturnsBooks() {
        var books = bookDaoJdbc.getByTitle("Laughing");
        assertEquals(1, books.size());
        var book = books.get(0);
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
    }

    @Test
    void whenPresentThenGetAllReturnsBooks() {
        var books = bookDaoJdbc.getAll();
        assertEquals(1, books.size());
        var book = books.get(0);
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
    }

    @Test
    void whenValidThenCreatePersistsBookRecord() {
        long persistedId = 2L;

        var book = BookFactory.buildBook();
        book.setId(persistedId);
        Assertions.assertDoesNotThrow(() -> bookDaoJdbc.create(book));
        var persistedBook = bookDaoJdbc.getById(persistedId);
        assertEquals(book.getTitle(), persistedBook.getTitle());
        assertEquals(book.getAuthor().getId(), persistedBook.getAuthor().getId());
        assertEquals(book.getGenre().getId(), persistedBook.getGenre().getId());
    }

    @Test
    void whenValidThenUpdateAltersBookRecordInDb() {
        long persistedId = 1L;
        var persistedBook = bookDaoJdbc.getById(persistedId);
        persistedBook.setTitle("Another Title");
        Assertions.assertDoesNotThrow(() -> bookDaoJdbc.update(persistedId, persistedBook));
        var updatedBook = bookDaoJdbc.getById(persistedId);

        assertEquals(persistedBook.getTitle(), updatedBook.getTitle());
    }

    @Test
    void whenPresentThenDeleteByIdDeletesBookRecord() {
        int bookCountBefore = bookDaoJdbc.getAll().size();
        long newBookId = bookCountBefore + 1;
        var book = BookFactory.buildBook();
        book.setId(newBookId);

        bookDaoJdbc.create(book);
        assertDoesNotThrow(() -> bookDaoJdbc.getById(newBookId));
        bookDaoJdbc.deleteById(newBookId);
        assertEquals(bookCountBefore, bookDaoJdbc.count());
        assertThrows(DataAccessException.class, () -> bookDaoJdbc.getById(newBookId));
    }
}