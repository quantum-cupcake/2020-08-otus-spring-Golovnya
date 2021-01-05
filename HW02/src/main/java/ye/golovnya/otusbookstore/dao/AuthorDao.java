package ye.golovnya.otusbookstore.dao;

import ye.golovnya.otusbookstore.entities.Author;
import ye.golovnya.otusbookstore.entities.Book;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);
}
