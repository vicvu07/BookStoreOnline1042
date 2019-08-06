package application.model.viewmodel.order;

/*import application.model.viewmodel.common.LayoutHeaderVM;*/

import application.model.viewmodel.cart.CartBookVM;

import java.util.List;

public class CheckoutVM {

    private List<CartBookVM> cartBookVMS ;

    public List<CartBookVM> getCartBookVMS() {
        return cartBookVMS;
    }

    public void setCartBookVMS(List<CartBookVM> cartBookVMS) {
        this.cartBookVMS = cartBookVMS;
    }
    /*private LayoutHeaderVM layoutHeaderVM;

    public LayoutHeaderVM getLayoutHeaderVM() {
        return layoutHeaderVM;
    }

    public void setLayoutHeaderVM(LayoutHeaderVM layoutHeaderVM) {
        this.layoutHeaderVM = layoutHeaderVM;
    }*/
}
