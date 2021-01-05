package ye.golovnya.otusbookstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
public class Genre {

    @Id
    private long id;

    private String name;

    @OneToMany(mappedBy = "genre")
    private Set<Book> books;
}
