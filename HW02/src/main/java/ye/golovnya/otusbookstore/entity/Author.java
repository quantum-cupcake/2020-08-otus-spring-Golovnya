package ye.golovnya.otusbookstore.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Author {

    @Id
    private long id;

    private String name;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;
}
