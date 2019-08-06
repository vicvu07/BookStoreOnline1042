package application.controller.web;

import application.data.model.*;
import application.data.service.CartBookService;
import application.data.service.CartService;
import application.data.service.OrderService;
/*import application.data.service.UserService;*/
import application.model.viewmodel.cart.CartBookVM;
import application.model.viewmodel.cart.CartVM;
import application.model.viewmodel.common.BookVM;
import application.model.viewmodel.order.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "/order")
public class OrderController extends BaseController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);

    /*@Autowired
    private UserService userService;*/

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartBookService cartBookService;

    @GetMapping("/checkout")
    public String checkout(Model model,
                           @Valid @ModelAttribute("bookname") BookVM bookName,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {
        CheckoutVM vm = new CheckoutVM();
        OrderVM order = new OrderVM();
        this.checkCookie(response,request,principal);
        List<CartBook> cartBookList = new ArrayList<>();
        List<CartBookVM> cartBookVMS= new ArrayList<>();



        String guid = getGuid(request);

        DecimalFormat df = new DecimalFormat("####0.00");

        try {
            if(guid != null) {
                Cart cartEntity = cartService.findFirstCartByGuid(guid);
                if(cartEntity != null) {
                    cartBookList = cartEntity.getListCartBooks();

                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        int totalPrice = 0;
        for (CartBook cartBook : cartBookList) {
            CartBookVM vm2 = new CartBookVM();
            vm2.setBookName(cartBook.getBook().getName());
            vm2.setAmount(cartBook.getAmount());
            vm2.setPrice(cartBook.getBook().getPrice() - cartBook.getBook().getPrice() * cartBook.getBook().getDiscount()/100);
            totalPrice += vm2.getPrice();

            cartBookVMS.add(vm2);
        }
        vm.setCartBookVMS(cartBookVMS);
        order.setTotalPrice(totalPrice);


        /*if(principal!= null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            User userEntity = userService.findUserByUsername(username);
            if(userEntity!= null) {
                order.setAddress(userEntity.getAddress());
                order.setCustomerName(userEntity.getName());
                order.setPhoneNumber(userEntity.getPhoneNumber());
                order.setEmail(userEntity.getEmail());
            }
        }*/


        model.addAttribute("order",order);
        model.addAttribute("vm",vm);

        return "/checkout";
    }

    @PostMapping("/checkout")
    public String checkout(@Valid @ModelAttribute("order") OrderVM orderVM,
                           @Valid @ModelAttribute("bookname") BookVM bookName,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           final Principal principal) {
        Order order = new Order();

        boolean flag = false;

        Cookie cookie[] = request.getCookies();

        String guid = null;

        if(cookie!=null) {
            for(Cookie c : cookie) {
                if(c.getName().equals("guid")) {
                    flag = true;
                    guid = c.getValue();
                }
            }
        }

        if(flag == true) {

            int totalPrice = 0;

            if(principal != null) {
                String  username = SecurityContextHolder.getContext().getAuthentication().getName();
                order.setUserName(username);
            }

            order.setGuid(guid);
            order.setAddress(orderVM.getAddress());
            order.setEmail(orderVM.getEmail());
            order.setPhoneNumber(orderVM.getPhoneNumber());
            order.setCustomerName(orderVM.getCustomerName());
            order.setCreatedDate(new Date());


            Cart cartEntity = cartService.findFirstCartByGuid(guid);
            if(cartEntity != null) {
                List<OrderBook> orderBooks = new ArrayList<>();
                for (CartBook cartBook : cartEntity.getListCartBooks()) {
                    OrderBook orderBook = new OrderBook();
                    orderBook.setOrder(order);
                    orderBook.setBook(cartBook.getBook());
                    orderBook.setAmount(cartBook.getAmount());

                    int price =  cartBook.getBook().getPrice() - cartBook.getBook().getDiscount()/100;
                    int priceDiscount = cartBook.getAmount() * price;
                    totalPrice += priceDiscount;

                    orderBook.setPrice(priceDiscount);

                    orderBooks.add(orderBook);
                }

                order.setListBookOrders(orderBooks);
                order.setPrice(totalPrice);

                orderService.addNewOrder(order);

                cartService.deleteCart(cartEntity.getId());

            }
        }

        return "redirect:/order/history";
    }

    @GetMapping("/history")
    public String orderHistory(Model model,
                               @Valid @ModelAttribute("bookname") BookVM bookName,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               final Principal principal) {

        OrderHistoryVM vm = new OrderHistoryVM();

        DecimalFormat df = new DecimalFormat("####0");


        List<OrderVM> orderVMS = new ArrayList<>();

        Cookie[] cookie = request.getCookies();

        String guid = null;
        boolean flag = false;

        List<Order> orderEntityList = null;

        if(principal != null) {
            String  username = SecurityContextHolder.getContext().getAuthentication().getName();
            orderEntityList = orderService.findOrderByGuidOrUserName(null,username);
        } else {
            if(cookie != null) {
                for(Cookie c : cookie) {
                    if(c.getName().equals("guid")) {
                        flag = true;
                        guid = c.getValue();
                    }
                }
                if(flag == true) {
                    orderEntityList = orderService.findOrderByGuidOrUserName(guid,null);
                }
            }
        }

        if(orderEntityList != null) {
            for(Order order : orderEntityList) {
                OrderVM orderVM = new OrderVM();
                orderVM.setId(order.getId());
                orderVM.setCustomerName(order.getCustomerName());
                orderVM.setEmail(order.getEmail());
                orderVM.setAddress(order.getAddress());
                orderVM.setPhoneNumber(order.getPhoneNumber());
                orderVM.setTotalPrice(order.getPrice());
                orderVM.setCreatedDate(order.getCreatedDate());

                orderVMS.add(orderVM);
            }
        }


        vm.setOrderVMS(orderVMS);

        model.addAttribute("vm",vm);

        return "/order-history";
    }


    @GetMapping("/history/{orderId}")
    public String getOrderDetail(Model model,
                                 @Valid @ModelAttribute("bookname") BookVM bookName,
                                 @PathVariable("orderId") int orderId) {

        OrderDetailVM vm = new OrderDetailVM();


        DecimalFormat df = new DecimalFormat("####0");

        double totalPrice = 0;

        List<OrderBookVM> orderBookVMS = new ArrayList<>();

        Order orderEntity = orderService.findOne(orderId);

        OrderVM vm2 = new OrderVM();
        vm2.setId(orderEntity.getId());
        vm2.setCustomerName(orderEntity.getCustomerName());
        vm2.setCreatedDate(orderEntity.getCreatedDate());



        if(orderEntity != null) {
            for(OrderBook orderBook : orderEntity.getListBookOrders()) {
                OrderBookVM orderBookVM = new OrderBookVM();

                orderBookVM.setBookId(orderBook.getBook().getId());
                orderBookVM.setMainImage(orderBook.getBook().getMainImage());
                orderBookVM.setAmount(orderBook.getAmount());
                orderBookVM.setName(orderBook.getBook().getName());

                orderBookVM.setPrice(df.format(orderBook.getPrice()));

                totalPrice += orderBook.getPrice();

                orderBookVMS.add(orderBookVM);
            }
        }


        vm.setOrderBookVMS(orderBookVMS);
        vm.setTotalPrice(df.format(totalPrice));
        vm.setTotalBook(orderBookVMS.size());

        model.addAttribute("vm",vm);
        model.addAttribute("vm2",vm2);

        return "/order-detail";
    }

}
