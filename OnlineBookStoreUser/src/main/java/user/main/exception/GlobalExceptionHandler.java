package user.main.exception;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import user.main.model.ErrorMassages;
import user.main.utility.Constant;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(UserIdNotFoundException.class)
	public ResponseEntity<Object> exceptionHandler(UserIdNotFoundException ex)
	{
		List<Object> list = new ArrayList<>();
		list.add("User Id Not Found");
		list.add("Error Massage : "+ex.getLocalizedMessage());
		list.add("Time Stamp : "+System.currentTimeMillis());
		return ResponseEntity.ok(new ErrorMassages(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"User Not Found By Id",list));
	}
	
	
	@ExceptionHandler(InternalServerErrorException.class)
	public ResponseEntity<Object> serverErrorException(InternalServerErrorException inex)
	{
		Map<String,String> mp=new HashMap<>();
		mp.put("ErrorDetails", "Internal Server Error");
		mp.put("ErrorMassage", inex.getLocalizedMessage());
		mp.put("TimeStamp", Long.toString(System.currentTimeMillis()));
		return ResponseEntity.ok(new ErrorMassages(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILURE,"Server Error",mp));
	}
	
	
	
}
