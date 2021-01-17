package ye.golovnya.otusbookstore.service.impl;

import org.springframework.stereotype.Service;
import ye.golovnya.otusbookstore.dao.AuthorDao;
import ye.golovnya.otusbookstore.entities.Author;
import ye.golovnya.otusbookstore.exceptions.AuthorNotFoundException;
import ye.golovnya.otusbookstore.service.AuthorService;

@Service
public class AuthorServiceJdbc implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceJdbc(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public Author getAuthor(Long id) {
        return getAuthorByIdOrThrow(id);
    }

    private Author getAuthorByIdOrThrow(Long id) {
        return authorDao.getById(id).orElseThrow(
                () -> new AuthorNotFoundException(id)
        );
    }
}
