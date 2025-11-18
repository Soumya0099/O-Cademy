package book.main.exception;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import book.main.model.ErrorMassages;
import book.main.utility.Constant;

@ControllerAdvice
public class GlobalExceptionHandler 
{	
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> serverErrorException(InternalServerErrorException inex)
	{
		Map<String,String> mp=new HashMap<>();
		mp.put("ErrorDetails", "Internal Server Error");
		mp.put("ErrorMassage", inex.getLocalizedMessage());
		mp.put("TimeStamp", Long.toString(System.currentTimeMillis()));
		return ResponseEntity.ok(new ErrorMassages(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILURE,"Server Error",mp));
	}
	
	
	@ExceptionHandler(BookIdNotExistException.class)
	public ResponseEntity<ErrorMassages> bookidnotfound(BookIdNotExistException bkex)
	{
		Map<Object,Object> hashMap = new HashMap<>();
		hashMap.put("Error Details", "Book Id Not Exist");
		hashMap.put("Error Massage", bkex.getLocalizedMessage());
		
		return ResponseEntity.ok(new ErrorMassages(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Book Not Found",hashMap));
	}

}
