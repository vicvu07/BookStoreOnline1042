package application.data.repository;

import application.data.model.BookAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookAuthorRepository extends JpaRepository<BookAuthor, Integer> {
    @Transactional(readOnly = true)
    @Query("select u from dbo_book_author u where u.bookId = :id")
    Iterable<BookAuthor> findAuthorOfBook(@Param("id") int bookId);
}
