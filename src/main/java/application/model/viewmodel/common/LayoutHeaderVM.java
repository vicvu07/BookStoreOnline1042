package application.model.viewmodel.common;


import java.util.ArrayList;

public class LayoutHeaderVM {

    private int cartQuantity;
    private ArrayList<HeaderMenuVM>  headerMenuVMArrayList;

    public ArrayList<HeaderMenuVM> getHeaderMenuVMArrayList() {
        return headerMenuVMArrayList;
    }


    public void setHeaderMenuVMArrayList(ArrayList<HeaderMenuVM> headerMenuVMArrayList) {
        this.headerMenuVMArrayList = headerMenuVMArrayList;
    }

    public int getCartQuantity() {
        return cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }
}
