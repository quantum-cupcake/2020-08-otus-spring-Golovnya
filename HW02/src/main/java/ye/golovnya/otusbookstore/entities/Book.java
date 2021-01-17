package ye.golovnya.otusbookstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Book {

    @Id
    @ToString.Include
    private long id;

    @ToString.Include
    private String title;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;
}
