package application.model.viewmodel.order;

/*import application.model.viewmodel.common.LayoutHeaderVM;*/

import java.util.List;

public class OrderDetailVM {

/*    private LayoutHeaderVM layoutHeaderVM;*/
    private List<OrderBookVM> orderBookVMS;
    private String totalPrice;
    private int totalBook;

   /* public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }*/

    public List<OrderBookVM> getOrderBookVMS() {
        return orderBookVMS;
    }

    public void setOrderBookVMS(List<OrderBookVM> orderBookVMS) {
        this.orderBookVMS = orderBookVMS;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalBook() {
        return totalBook;
    }

    public void setTotalBook(int totalBook) {
        this.totalBook = totalBook;
    }
}
