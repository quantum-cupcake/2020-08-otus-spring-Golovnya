package ye.golovnya.otusbookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ye.golovnya.otusbookstore.dao.BookDao;

@SpringBootApplication
public class OtusBookstoreApplication {

    public static void main(String[] args) {
        var c = SpringApplication.run(OtusBookstoreApplication.class, args);
        BookDao bookDaoJdbc = c.getBean("bookJpaRepository", BookDao.class);
        System.out.println(bookDaoJdbc.count());
        System.out.println(bookDaoJdbc.getById(1L));
    }
}
