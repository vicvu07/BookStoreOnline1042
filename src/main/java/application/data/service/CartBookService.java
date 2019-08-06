package application.data.service;

import application.data.model.CartBook;

import application.data.repository.CartBookRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartBookService {

    private static final Logger logger = LogManager.getLogger(CartBookService.class);

    @Autowired
    private CartBookRepository cartBookRepository;

    public void addNewCartBook(CartBook cartBook) {
        cartBookRepository.save(cartBook);
    }

    public boolean updateCartBook(CartBook cartBook) {
        try {
            cartBookRepository.save(cartBook);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public CartBook findOne(int cartBookId) {
        return cartBookRepository.findOne(cartBookId);
    }

    public boolean deleteCartBook(int cartBookId) {
        try {
            cartBookRepository.delete(cartBookId);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    @Transactional
    public boolean deleteListCartBooks(List<CartBook> cartBooks) {
        try {
            cartBookRepository.delete(cartBooks);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public CartBook findFirstCartBookByCartIdAndBookId(int cartId, int bookId) {
        try {
            return cartBookRepository.findFirstCartBookByCartIdAndBookId(cartId,bookId);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
