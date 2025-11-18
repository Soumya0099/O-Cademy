package book.main.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.main.entity.BookDetails;
import book.main.exception.BookIdNotExistException;
import book.main.exception.InternalServerErrorException;
import book.main.model.BookDetailsDTO;
import book.main.model.ResponseMassage;
import book.main.service.BookService;
import book.main.utility.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "CustomerController ",description = "Customer Can Regsiter, Update, Find And Getting All Customer Details ")


@RestController
@RequestMapping("/book")
public class BooksController 
{
	@Autowired
	BookService bookService;
	
	
	  @Operation(summary = "Save Books Details",description =
	  "e commerece online books store  register the Customer")
	  
	  @ApiResponses({
	  @ApiResponse(responseCode = "201",description = "Book Save successfully"),
	  @ApiResponse(responseCode = "400",description = "Book Save failure"), 
	  @ApiResponse(responseCode = "500",description = "Internal server error") })
	  
	  
	  @PostMapping("/savebooks") public ResponseEntity<ResponseMassage> savebooks(@RequestBody BookDetailsDTO bookDetailsDTO) 
	  { 
		  try
	  {
	  if(bookDetailsDTO.getBookname()==null ||
	  bookDetailsDTO.getBookname().isEmpty() ||
	  Integer.toString(bookDetailsDTO.getPrice()).isEmpty()) { return
	  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	  ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.
	  FAILED,"Book Name And Price Shouldnot empty or null")); }
	  
	  BookDetails insertbooks = bookService.insertbooks(bookDetailsDTO);
	  
	  if(insertbooks!=null) { return
	  ResponseEntity.status(HttpStatus.CREATED).body(new
	  ResponseMassage(HttpURLConnection.HTTP_OK,Constant.
	  SUCCESS,"Book Saved SuccessFully",insertbooks)); } else { return
	  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	  ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.
	  FAILED,"Something Went Wrong"));
	  
	  } } catch (Exception e) { throw new
	  InternalServerErrorException("Internal Server Error"); } }
	  
	  
	  
	  
	  @Operation(summary = "Find Books By Id",description =
	  "e commerece online books store  register the Customer")
	  
	  @ApiResponses({
	  
	  @ApiResponse(responseCode = "201",description =
	  "Book Find byId successfully"),
	  
	  @ApiResponse(responseCode = "400",description = "Book Find by Id failure"),
	  
	  @ApiResponse(responseCode = "500",description = "Internal server error") })
	  
	  @GetMapping("/findbookbyid/{id}")
	  public ResponseEntity<ResponseMassage>findbookbyid(@PathVariable Long id) 
	  { 
		  try 
		  { 
			  BookDetails findbookbyid = bookService.findbookbyid(id);
	  
	  if(findbookbyid!=null) 
	  { 
		  return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.  SUCCESS,"Book Find By id SuccessFully",findbookbyid)); 
		  } 
	  else 
	  { 
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant. FAILED,"Book Id Not Found  "));
	  
	  } 
	  } 
		  catch (Exception e) 
		  { 
			  throw new BookIdNotExistException("Book Id Not Found ");
			  } 
		  }
	 
	
	
	
	
	
	@Operation(summary = "Find All Books ",description = "e commerece online books store  register the Customer")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "Find All Books successfully"),
     @ApiResponse(responseCode = "400",description = "Find All Books failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
	
	
	@GetMapping("/getallbook")
	public ResponseEntity<ResponseMassage> getallbooks()
	{
		try
		{
			List<BookDetails> getallbookdetails = bookService.getallbookdetails();
			if(getallbookdetails!=null)
			{
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,"All Books Found SuccessFully",getallbookdetails));
			}
			else
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"No Book  Found  "));

			}
		}
		catch (Exception e) {
			throw new InternalServerErrorException("Internal Server Error");
		}
	}
	
	
	
	
	  @Operation(summary = "Delete Book ",description =
	  "e commerece online books store  register the Customer")
	  
	  @ApiResponses({
	  
	  @ApiResponse(responseCode = "201",description = " Book delete successfully"),
	  
	  @ApiResponse(responseCode = "400",description = "Book delete failure"),
	  
	  @ApiResponse(responseCode = "500",description = "Internal server error") })
	  
	  
	  
	  @DeleteMapping("/deletebook/{id}") public ResponseEntity<ResponseMassage>
	  deletebook(@PathVariable Long id) { try { String deletebook =
	  bookService.deletebook(id);
	  
	  if(deletebook!=null) { return
	  ResponseEntity.status(HttpStatus.CREATED).body(new
	  ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,deletebook));
	  
	  } else { return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new
	  ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.
	  FAILED,"Book Id Not Found  "));
	  
	  } } catch (Exception e) { throw new
	  BookIdNotExistException("Book Id Not Exist "); }
	  
	  }
	 

}
