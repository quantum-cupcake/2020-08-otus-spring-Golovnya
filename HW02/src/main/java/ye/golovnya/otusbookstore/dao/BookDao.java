package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Book;

import java.util.List;

public interface BookDao {

    int count();

    void create(Book book);

    void update(long id, Book changedBook);

    Book getById(long id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    void deleteById(long id);
}
