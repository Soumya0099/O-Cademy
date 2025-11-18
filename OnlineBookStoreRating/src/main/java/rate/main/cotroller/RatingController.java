package rate.main.cotroller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rate.main.entity.Rating;
import rate.main.model.RatingDto;
import rate.main.model.ResponseMassage;
import rate.main.service.RatingService;
import rate.main.utility.Constant;

@RestController
@RequestMapping("/rating")
public class RatingController 
{
	@Autowired private RatingService ratingService;
	
	@PostMapping("/createrating")
	public ResponseEntity<ResponseMassage> createrating(@RequestBody RatingDto ratingDto)
	{
		if(Long.toString(ratingDto.getCustomerid()).isEmpty() ||  Long.toString(ratingDto.getBookid()).isEmpty()) 
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Customerid and Book id Must be Entered"));
		}
		
		if(ratingDto.getStar()>=6)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Rating accepted by Maximun 5 Star"));

		}
		
		Rating saverating = ratingService.saverating(ratingDto);
		
		if(saverating!=null)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"Thank you For Your Feedback ..... ! ",saverating));

		}
		else
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Something Went Wrong"));

		}
		
	}
	
	
	@GetMapping("/getallrating")
	public ResponseEntity<ResponseMassage> getallratings()
	{
		List<Rating> getallrating = ratingService.getallrating();
		
		return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"All Ratings getting SuccessFully ",getallrating));
	}
	
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseMassage> deleterating(@PathVariable Long id)
	{
		String deleterating = ratingService.deleterating(id);
		return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"All Ratings getting SuccessFully ",deleterating));

	}

}
