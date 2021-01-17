package ye.golovnya.otusbookstore.dao.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import ye.golovnya.otusbookstore.factory.BookFactory;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Import(BookJpaRepository.class)
@DataJpaTest
class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenCountReturns() {
        long count = bookJpaRepository.count();
        assertEquals(1L, count);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenGetByIdReturnsBookWithComments() {
        var persistedId = (long) bookJpaRepository.getAll().size();
        var bookOpt = bookJpaRepository.getById(persistedId);
        if (bookOpt.isEmpty()) {
            fail("Книга не найдена");
        }
        var book = bookOpt.get();
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
        assertNotNull(book.getComments());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void whenPresentThenGetByTitleReturnsBooksWithComments() {
        var books = bookJpaRepository.getByTitle("Laughing");
        assertEquals(1, books.size());
        var book = books.get(0);
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
        assertNotNull(book.getComments());
    }

    @Test
    void whenPresentThenGetAllReturnsBooksWithComments() {
        var books = bookJpaRepository.getAll();
        assertEquals(1, books.size());
        var book = books.get(0);
        assertEquals("The Laughing Man", book.getTitle());
        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
        assertEquals("Young Adult", book.getGenre().getName());
        assertNotNull(book.getComments());
    }

    @Test
    void whenValidThenCreatePersistsBookRecord() {

        var persistedId = (long) bookJpaRepository.getAll().size() + 1;

        var book = BookFactory.buildBook();
        assertDoesNotThrow(() -> bookJpaRepository.create(book));
        var persistedBookOpt = bookJpaRepository.getById(persistedId);
        if (persistedBookOpt.isEmpty()) {
            fail("Книга не найдена после сохранения");
        }
        var persistedBook = persistedBookOpt.get();
        assertEquals(book.getTitle(), persistedBook.getTitle());
        assertEquals(book.getAuthor().getId(), persistedBook.getAuthor().getId());
        assertEquals(book.getGenre().getId(), persistedBook.getGenre().getId());
    }
//
    @Test
    void whenValidThenUpdateAltersBookRecordInDb() {
        var persistedId = (long) bookJpaRepository.getAll().size();
        var persistedBookOpt = bookJpaRepository.getById(persistedId);
        if (persistedBookOpt.isEmpty()) {
            fail("Книга для обновления не найдена");
        }
        var persistedBook = persistedBookOpt.get();
        persistedBook.setTitle("Another Title");
        assertDoesNotThrow(() -> bookJpaRepository.update(persistedBook));
        var updatedBookOpt = bookJpaRepository.getById(persistedId);
        if (updatedBookOpt.isEmpty()) {
            fail("Книга не найдена после обновления");
        }
        var updatedBook = updatedBookOpt.get();
        assertEquals(persistedBook.getTitle(), updatedBook.getTitle());
    }

    @Test
    void whenPresentThenDeleteByIdDeletesBookRecord() {
        int bookCountBefore = bookJpaRepository.getAll().size();
        var persistedId = (long) bookCountBefore + 1;
        var book = BookFactory.buildBook();

        assertTrue(bookJpaRepository.getByTitle(book.getTitle()).isEmpty());
        bookJpaRepository.create(book);
        var newBookOpt = bookJpaRepository.getById(persistedId);
        if (newBookOpt.isEmpty()) {
            fail("Книга для удаления не найдена");
        }
        var newBook = newBookOpt.get();
        bookJpaRepository.deleteById(newBook.getId());
        assertEquals(bookCountBefore, bookJpaRepository.count());
        assertTrue(bookJpaRepository.getById(newBook.getId()).isEmpty());
    }
}