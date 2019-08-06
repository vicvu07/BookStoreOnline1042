package application.controller.api;


import application.data.model.Book;
import application.data.model.Cart;
import application.data.model.CartBook;

import application.data.service.BookService;
import application.data.service.CartBookService;

import application.data.service.CartService;

import application.model.api.BaseApiResult;
import application.model.dto.CartBookDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/cart-book")
public class CartBookApiController {

    private static final Logger logger = LogManager.getLogger(CategoryApiController.class);

    @Autowired
    private CartService cartService;

    @Autowired
    private CartBookService cartBookService;

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public BaseApiResult addToCart(@RequestBody CartBookDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(dto.getGuid() != null && dto.getAmount() > 0 && dto.getBookId() > 0) {
                Cart cartEntity = cartService.findFirstCartByGuid(dto.getGuid());
                Book bookEntity = bookService.findOne(dto.getBookId());
                if(cartEntity != null && bookEntity != null) {
                    CartBook cartBookEntity = cartBookService.findFirstCartBookByCartIdAndBookId(cartEntity.getId(),bookEntity.getId());
                    if(cartBookEntity != null) {
                        cartBookEntity.setAmount(cartBookEntity.getAmount() + dto.getAmount());
                        cartBookService.updateCartBook(cartBookEntity);
                    } else {
                        CartBook cartBook = new CartBook();
                        cartBook.setAmount(dto.getAmount());
                        cartBook.setCart(cartEntity);
                        cartBook.setBook(bookEntity);
                        cartBookService.addNewCartBook(cartBook);
                    }
                    result.setMessage("Add to cart successfully!");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Thêm giỏ hàng không thành công!");
        result.setSuccess(false);
        return result;
    }

    @PostMapping("/update")
    public BaseApiResult updateCartBook(@RequestBody CartBookDTO dto) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(dto.getId()>0 && dto.getAmount() > 0) {
                CartBook cartBookEntity = cartBookService.findOne(dto.getId());

                if(cartBookEntity != null) {
                    cartBookEntity.setAmount(dto.getAmount());
                    cartBookService.updateCartBook(cartBookEntity);
                    result.setMessage("Update Cart Book success !");
                    result.setSuccess(true);
                    return result;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setMessage("Số lượng tối thiểu là: 1");
        result.setSuccess(false);
        return result;
    }

    @GetMapping("/delete/{cartBookId}")
    public BaseApiResult deleteCartBook(@PathVariable int cartBookId) {
        BaseApiResult result = new BaseApiResult();

        try {
            if(cartBookService.deleteCartBook(cartBookId) == true) {
                result.setMessage("Hủy mua thành công");
                result.setSuccess(true);
                return result;
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        result.setSuccess(false);
        result.setMessage("Fail!");
        return result;
    }

}
