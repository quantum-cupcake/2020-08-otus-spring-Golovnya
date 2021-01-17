package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    long count();

    void create(Book book);

    void update(Book changedBook);

    Optional<Book> getById(Long id);

    List<Book> getByTitle(String title);

    List<Book> getAll();

    void deleteById(long id);
}
