//package ye.golovnya.otusbookstore.dao.impl;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.DataAccessException;
//import org.springframework.test.annotation.DirtiesContext;
//import ye.golovnya.otusbookstore.factory.BookFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//@Import(BookJpaRepository.class)
//@JdbcTest
//class BookJpaRepositoryTest {
//
//    @Autowired
//    private BookJpaRepository bookJpaRepository;
//
//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    void whenPresentThenCountReturns() {
//        int count = bookJpaRepository.count();
//        assertEquals(1, count);
//    }
//
//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    void whenPresentThenGetByIdReturnsBook() {
//        var book = bookJpaRepository.getById(1L);
//        assertEquals("The Laughing Man", book.getTitle());
//        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
//        assertEquals("Young Adult", book.getGenre().getName());
//    }
//
//    @Test
//    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
//    void whenPresentThenGetByTitleReturnsBooks() {
//        var books = bookJpaRepository.getByTitle("Laughing");
//        assertEquals(1, books.size());
//        var book = books.get(0);
//        assertEquals("The Laughing Man", book.getTitle());
//        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
//        assertEquals("Young Adult", book.getGenre().getName());
//    }
//
//    @Test
//    void whenPresentThenGetAllReturnsBooks() {
//        var books = bookJpaRepository.getAll();
//        assertEquals(1, books.size());
//        var book = books.get(0);
//        assertEquals("The Laughing Man", book.getTitle());
//        assertEquals("Jerome D. Salinger", book.getAuthor().getName());
//        assertEquals("Young Adult", book.getGenre().getName());
//    }
//
//    @Test
//    void whenValidThenCreatePersistsBookRecord() {
//        long persistedId = 2L;
//
//        var book = BookFactory.buildBook();
//        book.setId(persistedId);
//        Assertions.assertDoesNotThrow(() -> bookJpaRepository.create(book));
//        var persistedBook = bookJpaRepository.getById(persistedId);
//        assertEquals(book.getTitle(), persistedBook.getTitle());
//        assertEquals(book.getAuthor().getId(), persistedBook.getAuthor().getId());
//        assertEquals(book.getGenre().getId(), persistedBook.getGenre().getId());
//    }
//
//    @Test
//    void whenValidThenUpdateAltersBookRecordInDb() {
//        long persistedId = 1L;
//        var persistedBook = bookJpaRepository.getById(persistedId);
//        persistedBook.setTitle("Another Title");
//        Assertions.assertDoesNotThrow(() -> bookJpaRepository.update(persistedBook));
//        var updatedBook = bookJpaRepository.getById(persistedId);
//
//        assertEquals(persistedBook.getTitle(), updatedBook.getTitle());
//    }
//
//    @Test
//    void whenPresentThenDeleteByIdDeletesBookRecord() {
//        int bookCountBefore = bookJpaRepository.getAll().size();
//        long newBookId = bookCountBefore + 1;
//        var book = BookFactory.buildBook();
//        book.setId(newBookId);
//
//        bookJpaRepository.create(book);
//        assertDoesNotThrow(() -> bookJpaRepository.getById(newBookId));
//        bookJpaRepository.deleteById(newBookId);
//        assertEquals(bookCountBefore, bookJpaRepository.count());
//        assertThrows(DataAccessException.class, () -> bookJpaRepository.getById(newBookId));
//    }
//}