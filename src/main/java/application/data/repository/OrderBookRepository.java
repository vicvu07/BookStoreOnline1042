package application.data.repository;

import application.data.model.OrderBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderBookRepository extends JpaRepository<OrderBook,Integer> {
}
