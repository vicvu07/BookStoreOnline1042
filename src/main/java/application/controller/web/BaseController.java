package application.controller.web;


import application.data.model.Cart;
import application.data.model.CartBook;
import application.data.service.CartService;
import application.model.viewmodel.cart.CartBookVM;
import application.model.viewmodel.cart.CartVM;
import application.model.viewmodel.common.BookVM;
import application.model.viewmodel.common.HeaderMenuVM;
import application.model.viewmodel.common.LayoutHeaderAdminVM;
import application.model.viewmodel.common.LayoutHeaderVM;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class BaseController {

    @Autowired
    private CartService cartService;

    /*@Autowired
    private UserService userService;*/


    private static final Logger logger = LogManager.getLogger(CartController.class);

    public void checkCookie(HttpServletResponse response,
                            HttpServletRequest request,
                            final Principal principal) {
        Cookie cookie[] = request.getCookies();


        if (principal != null) {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            Cart cartEntity = cartService.findByUserName(username);
            if (cartEntity != null) {
                Cookie cookie1 = new Cookie("guid", cartEntity.getGuid());
                cookie1.setPath("/");
                response.addCookie(cookie1);
            } else {
                UUID uuid = UUID.randomUUID();
                String guid = uuid.toString();
                Cart cart = new Cart();
                cart.setGuid(guid);
                cart.setUserName(username);
                cartService.addNewCart(cart);

                Cookie cookie2 = new Cookie("guid", guid);
                cookie2.setPath("/");
                response.addCookie(cookie2);
            }
        } else {
            boolean flag2 = true;

            String guid = null;

            if (cookie != null) {
                for (Cookie c : cookie) {
                    if (c.getName().equals("guid")) {
                        flag2 = false;
                        guid = c.getValue();
                    }
                }
            }

            if (flag2 == true) {
                UUID uuid = UUID.randomUUID();
                String guid2 = uuid.toString();
                Cart cart2 = new Cart();
                cart2.setGuid(guid2);
                cartService.addNewCart(cart2);

                Cookie cookie3 = new Cookie("guid", guid2);
                cookie3.setPath("/");
                cookie3.setMaxAge(60 * 60 * 24);
                response.addCookie(cookie3);

            } else {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if (cartEntity == null) {
                    Cart cart3 = new Cart();
                    cart3.setGuid(guid);
                    cartService.addNewCart(cart3);
                }
            }

        }

    }

    public int cartQuantity(@Valid @ModelAttribute("bookname") BookVM bookName,
                             HttpServletResponse response,
                             HttpServletRequest request,
                             final Principal principal) {

        this.checkCookie(response, request, principal);

        CartVM vm = new CartVM();

        int bookAmount = 0;
        double totalPrice = 0;
        List<CartBookVM> cartBookVMS = new ArrayList<>();

        String guid = getGuid(request);

        DecimalFormat df = new DecimalFormat("####0.00");

        try {
            if (guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if (cartEntity != null) {
                    bookAmount = cartEntity.getListCartBooks().size();
                }
            }

        } catch (
                Exception e) {
            logger.error(e.getMessage());
        }
    return bookAmount;
    }

    public String getGuid(HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if (cookie != null) {
            for (Cookie c : cookie) {
                if (c.getName().equals("guid")) return c.getValue();
            }
        }
        return null;
    }


    public LayoutHeaderVM getLayoutHeaderVM() {


        LayoutHeaderVM vm = new LayoutHeaderVM();

        ArrayList<HeaderMenuVM> headerMenuVMArrayList = new ArrayList<>();
        headerMenuVMArrayList.add(new HeaderMenuVM("Home", "/"));
        headerMenuVMArrayList.add(new HeaderMenuVM("Contact", "/"));

        vm.setHeaderMenuVMArrayList(headerMenuVMArrayList);
        vm.setCartQuantity(7);


        return vm;
    }


    public LayoutHeaderAdminVM getLayoutHeaderAdminVM() {

        LayoutHeaderAdminVM vm = new LayoutHeaderAdminVM();

        /*String  username = SecurityContextHolder.getContext().getAuthentication().getName();
        User userEntity = userService.findUserByUsername(username);

        if(userEntity!=null) {
            vm.setUserName(username);
            if(userEntity.getAvatar() != null) {
                vm.setAvatar(userEntity.getAvatar());
            } else vm.setAvatar("https://aets.org.es/wp-content/uploads/2014/12/omita-el-icono-del-perfil-avatar-placeholder-gris-de-la-foto-99724602.jpg");
        }*/

        return vm;

    }

}
