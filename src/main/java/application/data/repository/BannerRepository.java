package application.data.repository;

import application.data.model.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner,Integer> {
}
