package rate.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rate.main.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Long>{

}
