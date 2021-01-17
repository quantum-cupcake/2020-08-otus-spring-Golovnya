package ye.golovnya.otusbookstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id")
    @SequenceGenerator(name = "author_id", sequenceName = "bookstore.author_id_seq", allocationSize = 50, initialValue = 2)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author")
    private Set<Book> books;
}
