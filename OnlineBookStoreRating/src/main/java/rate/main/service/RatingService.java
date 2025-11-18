package rate.main.service;

import java.util.List;

import rate.main.entity.Rating;
import rate.main.model.RatingDto;

public interface RatingService {

	Rating saverating(RatingDto ratingDto);

	List<Rating> getallrating();

	String deleterating(Long id);

}
