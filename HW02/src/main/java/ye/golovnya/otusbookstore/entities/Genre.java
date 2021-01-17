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
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id")
    @SequenceGenerator(name = "genre_id", sequenceName = "bookstore.genre_id_seq", allocationSize = 50)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;
}
