package ye.golovnya.otusbookstore.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@NamedEntityGraph(name = Book.COMMENTS_GRAPH_NAME, attributeNodes = {@NamedAttributeNode(value = "comments")})
public class Book {

    public static final String COMMENTS_GRAPH_NAME = "book-comments-graph";

    @Id
    @ToString.Include
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id")
    @SequenceGenerator(name = "book_id", sequenceName = "bookstore.book_id_seq", allocationSize = 50)
    private Long id;

    @ToString.Include
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Include
    private List<Comment> comments;
}
