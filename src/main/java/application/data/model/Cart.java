package application.data.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "dbo_cart")
public class Cart {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    @Id
    private int id;

    @Column(name = "guid")
    private String guid;

    @Column(name = "username")
    private String userName;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
    private List<CartBook> listCartBooks = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<CartBook> getListCartBooks() {
        return listCartBooks;
    }

    public void getListCartBooks(List<CartBook> getListCartBooks) {
        this.listCartBooks = listCartBooks;
    }
}
