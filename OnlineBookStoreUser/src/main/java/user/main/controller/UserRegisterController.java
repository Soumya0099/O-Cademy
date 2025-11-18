package user.main.controller;

import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import user.main.entity.UserRegister;
import user.main.model.ResponseMassage;
import user.main.model.UserRegisterDTO;
import user.main.service.UserRegisterService;
import user.main.utility.Constant;


@Tag(name = "UserRegisterController ",description = "User Register and Login")

@RestController
@RequestMapping("/user")
public class UserRegisterController 
{

	@Autowired
	private UserRegisterService userregisterservice;

	
	@Operation(summary = "Create User Registration",description = "This Api for User Registration")
	@ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user register successfully"),
	     @ApiResponse(responseCode = "400",description = "user register failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	
	@PostMapping("/userregister")
	public ResponseEntity<ResponseMassage> createUserRegister(@RequestBody UserRegisterDTO userRegisterDTO)
	{
		try
		{
			if(userRegisterDTO.getEmail()==null || userRegisterDTO.getEmail().isEmpty() || userRegisterDTO.getPassword()==null || userRegisterDTO.getPassword().isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Email & Password Cannot be Empty"));
			}
			UserRegister userregister=userregisterservice.insertUserRegister(userRegisterDTO);
			if(userregister!=null)
			{
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"SuccessFully Registered",userregister));
			}
			else
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Registration UnseccessFull"));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILED,"Internal Server Error"));
		}
		
	}
	
	
	@Operation(summary = "User Login",description = "This Api Check UserDetails")
	@ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user Login successfully"),
	     @ApiResponse(responseCode = "400",description = "user Login failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	
	@GetMapping("/userlogin")
	public ResponseEntity<ResponseMassage> getUserDetails(@RequestBody UserRegisterDTO userRegisterDTO )
	{
		try
		{
			if(userRegisterDTO.getEmail()==null|| userRegisterDTO.getEmail().isEmpty()|| userRegisterDTO.getPassword()==null||userRegisterDTO.getPassword().isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Email & Password Required"));
			}
			else
			{
				UserRegister userdetails = userregisterservice.checkdetails(userRegisterDTO);
				if(userdetails!=null)
				{
					String pass=new String(Base64.getDecoder().decode(userdetails.getPassword()));
					return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"SuccessFully Login",userdetails,pass));
				}
				else
				{
					return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILURE,"Invalid Credentials or User Doesnot Exist"));
				}
			}
		}	
		catch(Exception e)
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILED,"Internal Server Error"));
		}
	}
	
	@Operation(summary = "Find User Id",description = "This Api for Find User Through Id")
	@ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user Find successfully"),
	     @ApiResponse(responseCode = "400",description = "user Find failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })

	@GetMapping("/finduserbyid/{id}")
	public UserRegister getuserbyid(@PathVariable Long id)
	{
		UserRegister userbyid = userregisterservice.checkuserbyid(id);
		return userbyid;
	}
	
	
	@Operation(summary = "Get All User ",description = "This Api for Find All User")
	@ApiResponses({
	     @ApiResponse(responseCode = "201",description = "user Find successfully"),
	     @ApiResponse(responseCode = "400",description = "user Find failure"),
	     @ApiResponse(responseCode = "500",description = "Internal server error")
	     })
	
	
	@GetMapping("/getalluser")
	public List<UserRegister> getalluser()
	{
		List<UserRegister> list = userregisterservice.getalluserdetails();
		return list;
	}
}
