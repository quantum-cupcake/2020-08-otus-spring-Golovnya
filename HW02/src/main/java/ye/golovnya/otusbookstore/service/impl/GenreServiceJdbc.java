package ye.golovnya.otusbookstore.service.impl;

import org.springframework.stereotype.Service;
import ye.golovnya.otusbookstore.dao.GenreDao;
import ye.golovnya.otusbookstore.entities.Genre;
import ye.golovnya.otusbookstore.exceptions.GenreNotFoundException;
import ye.golovnya.otusbookstore.service.GenreService;

@Service
public class GenreServiceJdbc implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceJdbc(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre getGenre(Long id) {
        return getGenreByIdOrThrow(id);
    }

    private Genre getGenreByIdOrThrow(Long id) {
        return genreDao.getById(id).orElseThrow(
                () -> new GenreNotFoundException(id)
        );
    }
}
