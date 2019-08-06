package application.model.viewmodel.cart;


import java.util.List;

public class CartVM {

    private int bookAmount;
    private List<CartBookVM> cartBookVMS;
    private int cartQuantity;
    private int totalPrice;


    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }


    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public List<CartBookVM> getCartBookVMS() {
        return cartBookVMS;
    }

    public void setCartBookVMS(List<CartBookVM> cartBookVMS) {
        this.cartBookVMS = cartBookVMS;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
