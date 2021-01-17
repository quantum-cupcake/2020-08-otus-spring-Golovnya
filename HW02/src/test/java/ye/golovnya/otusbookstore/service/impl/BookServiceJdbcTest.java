//package ye.golovnya.otusbookstore.service.impl;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import ye.golovnya.otusbookstore.dao.BookDao;
//import ye.golovnya.otusbookstore.entities.Author;
//import ye.golovnya.otusbookstore.entities.Book;
//import ye.golovnya.otusbookstore.factory.BookFactory;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class BookServiceJdbcTest {
//
//    @Mock
//    private BookDao bookDao;
//
//    @InjectMocks
//    private BookServiceJdbc bookServiceJdbc;
//
//    @Test
//    void getBook() {
//        var expectedBook = BookFactory.buildBook();
//
//        when(bookDao.getById(expectedBook.getId()))
//                .thenReturn(expectedBook);
//
//        var actual = bookServiceJdbc.getBook(expectedBook.getId());
//
//        assertEquals(expectedBook, actual);
//    }
//
//    @Test
//    void getBooksByTitle() {
//    }
//
//    @Test
//    void getAllBooks() {
//    }
//
//    @Test
//    void createBook() {
//    }
//
//    @Test
//    void updateBook() {
//    }
//
//    @Test
//    void deleteBook() {
//    }
//}