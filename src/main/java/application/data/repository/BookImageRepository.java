package application.data.repository;

import application.data.model.BookImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookImageRepository extends JpaRepository<BookImage, Integer> {
}
