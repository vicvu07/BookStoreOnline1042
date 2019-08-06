package application.data.model;

import javax.persistence.*;

@Entity(name = "dbo_cart_book")
public class CartBook {

    @GeneratedValue(strategy =  GenerationType.AUTO)
    @Column(name = "cart_book_id")
    @Id
    private int id;


    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @Column(name = "cart_id", insertable = false, updatable = false)
    private int cartId;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "book_id", insertable = false, updatable = false)
    private int bookId;

    @Column(name = "amount")
    private int amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
