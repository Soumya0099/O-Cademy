package rate.main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rate.main.entity.BookDetails;
import rate.main.entity.Customer;
import rate.main.entity.Rating;
import rate.main.exception.BookIdNotExistException;
import rate.main.exception.CustomerNotFoundException;
import rate.main.model.RatingDto;
import rate.main.repository.BookRepo;
import rate.main.repository.CustomerRepo;
import rate.main.repository.RatingRepo;
import rate.main.service.RatingService;

@Service
public class RatingServiceImpl implements RatingService
{
	@Autowired private RatingRepo ratingRepo;
	@Autowired private CustomerRepo customerRepo;
	@Autowired private BookRepo bookRepo;

	@Override
	public Rating saverating(RatingDto ratingDto) 
	{
		 Customer existcustomer = customerRepo.findById(ratingDto.getCustomerid()).orElseThrow(()->new CustomerNotFoundException("Customer Not Exist"));
		 BookDetails existbook = bookRepo.findById(ratingDto.getBookid()).orElseThrow(()->new BookIdNotExistException("Book Not Exist"));
		
		
		if(existcustomer!=null && existbook!=null)
		{
			Rating rating = new Rating();
			rating.setBookid(ratingDto.getBookid());
			rating.setCustomerid(ratingDto.getCustomerid());
			rating.setStar(ratingDto.getStar());
			rating.setDescription(ratingDto.getDescription());
			
			return ratingRepo.save(rating);
		}
		else
		{
			return null;
		}
	}

	@Override
	public List<Rating> getallrating() 
	{
		List<Rating> allrating = ratingRepo.findAll();
		
		return allrating;
		
	}

	@Override
	public String deleterating(Long id)
	{
		ratingRepo.deleteById(id);
		
		return "SuccessFully Deleted ........ ";
	}

}
