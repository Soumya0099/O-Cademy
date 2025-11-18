package cart.main.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cart.main.entity.BooksCartModule;
import cart.main.model.ResponseMassage;
import cart.main.service.BooksCartModuleService;
import cart.main.utility.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/cart")
public class BooksCartModuleController 
{
	@Autowired
	private BooksCartModuleService booksCartModuleService;
	
	
	@Operation(summary = "Creating Cart Module",description = "This Api for Adding Books To Cart")
	@ApiResponses({
	     @ApiResponse(responseCode = "201",description = "Cart Created successfully"),
	     @ApiResponse(responseCode = "400",description = "Cart Created failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	
	
	@PostMapping("/addtocart")
	public ResponseEntity<ResponseMassage> createCart(@RequestParam int quantity,
			                                          @RequestParam Long bookid, @RequestParam Long customerid)
	{
		try
		{
			BooksCartModule finalcart = booksCartModuleService.addbookdtocart(quantity,bookid,customerid);
			
			if(finalcart!=null)
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,"Item Succesfully Added To Cart",finalcart));
			}
			else
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Item Added To Cart Failed"));
			}
		}
		catch (Exception e) 
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILURE,"Book Id or Customer Id not Found"));
		}
		
	}
	
	
	@Operation(summary = "Get All Cart Product",description = "This Api for Showing all products present inside cart")
	
	@GetMapping("/viewcart")
	public ResponseEntity<ResponseMassage> showallitemfromCart()
	{
		try
		{
			List<BooksCartModule> showcartitems = booksCartModuleService.showcartitems();
			if(showcartitems!=null)
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,"Item On Cart",showcartitems));

			}
			else
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,"No Items Present in Cart"));
			}
			
		}
		catch (Exception e) {
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILURE,"Internal Server Error"));
		}
	}

}
