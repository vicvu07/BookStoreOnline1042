package application.data.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name="dbo_author")
public class Author {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id")
    @Id
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "short_desc")
    private String shortDesc;



    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "dbo_book_author",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")})
    private Set<Book> listAuthor = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
    public Set<Book> getListAuthor() {
        return listAuthor;
    }

    public void setListAuthor(Set<Book> listAuthor) {
        this.listAuthor = listAuthor;
    }

}
