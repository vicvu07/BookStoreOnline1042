package application.controller.web;

import application.data.model.Cart;
import application.data.model.CartBook;

import application.data.service.CartService;
import application.model.viewmodel.cart.CartBookVM;

import application.model.viewmodel.cart.CartVM;
import application.model.viewmodel.common.BookVM;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/cart")
public class CartController extends BaseController {

    private static final Logger logger = LogManager.getLogger(CartController.class);


    @Autowired
    private CartService cartService;

    @GetMapping("")
    public String cart(Model model,
                       @Valid @ModelAttribute("bookname") BookVM bookName,
                       HttpServletResponse response,
                       HttpServletRequest request,
                       final Principal principal) {

        this.checkCookie(response,request,principal);

        CartVM vm = new CartVM();

        int bookAmount = 0;
        int totalPrice = 0;
        List<CartBookVM> cartBookVMS = new ArrayList<>();

        String guid = getGuid(request);

        DecimalFormat df = new DecimalFormat("####0.00");

        try {
            if(guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if(cartEntity != null) {
                    bookAmount = cartEntity.getListCartBooks().size();
                    for(CartBook cartBook : cartEntity.getListCartBooks()) {
                        CartBookVM cartBookVM = new CartBookVM();
                        cartBookVM.setId(cartBook.getId());
                        cartBookVM.setBookId(cartBook.getBook().getId());
                        cartBookVM.setBookName(cartBook.getBook().getName());
                        cartBookVM.setMainImage(cartBook.getBook().getMainImage());
                        cartBookVM.setAmount(cartBook.getAmount());
                        int priceOfThisBook = cartBook.getBook().getPrice() - cartBook.getBook().getPrice()*cartBook.getBook().getDiscount()/100 ;
                        cartBookVM.setPrice(priceOfThisBook);
                        int priceOfAll = cartBook.getAmount()*priceOfThisBook;
                        totalPrice += priceOfAll;
                        cartBookVMS.add(cartBookVM);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }



        vm.setBookAmount(bookAmount);
        vm.setCartBookVMS(cartBookVMS);
        vm.setTotalPrice(totalPrice);
        vm.setCartQuantity(cartBookVMS.size());

        model.addAttribute("vm",vm);

        return "/cart";
    }

    public String getGuid(HttpServletRequest request) {
        Cookie cookie[] = request.getCookies();

        if(cookie!=null) {
            for(Cookie c :cookie) {
                if(c.getName().equals("guid"))  return c.getValue();
            }
        }
        return null;
    }

}
