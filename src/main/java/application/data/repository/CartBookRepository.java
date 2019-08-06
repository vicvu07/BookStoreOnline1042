package application.data.repository;

import application.data.model.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartBookRepository extends JpaRepository<CartBook,Integer> {

    @Query(value = "SELECT * FROM dbo_cart_book cp " +
            "WHERE cp.cart_id = :cartId  " +
            "AND cp.book_id = :bookId " +
            "ORDER BY cp.cart_book_id DESC LIMIT 1",nativeQuery = true)
    CartBook findFirstCartBookByCartIdAndBookId(@Param("cartId")int cartId, @Param("bookId") int bookId);
}


