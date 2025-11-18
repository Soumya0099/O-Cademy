package cust.main.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cust.main.entity.Customer;
import cust.main.model.ResponseMassage;
import cust.main.repository.CustomerRepo;
import cust.main.service.CustomerService;
import cust.main.serviceImpl.CustomerServiceImpl;
import cust.main.utility.Constant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.InternalServerErrorException;


@Tag(name = "CustomerController ",description = "Customer Can Regsiter, Update, Find And Getting All Customer Details ")

@RestController
@RequestMapping("/customer")
public class CustomerController 
{

    private final CustomerServiceImpl customerServiceImpl;

    private final CustomerRepo customerRepo;
	@Autowired
	private CustomerService customerService;

    CustomerController(CustomerRepo customerRepo, CustomerServiceImpl customerServiceImpl) {
        this.customerRepo = customerRepo;
        this.customerServiceImpl = customerServiceImpl;
    }
    
    
    
    @Operation(summary = "Create Customer Register",description = "e commerece online books store  register the Customer")
    @ApiResponses({
     @ApiResponse(responseCode = "201",description = "Customer register successfully"),
     @ApiResponse(responseCode = "400",description = "Customer register failure"),
     @ApiResponse(responseCode = "500",description = "Internal server error")
     })
    
	@PostMapping("/savecustomer")
	public ResponseEntity<ResponseMassage> saveCustomer(@RequestBody Customer customer)
	{
		try
		{
			if(customer.getFname()==null || customer.getFname().isEmpty() || customer.getMail()==null || customer.getMail().isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"First Name And Mail Shouldnot empty or null"));
			}
			Customer insertCustomer = customerService.insertCustomer(customer);
			if(insertCustomer!=null)
			{
				return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"Customer Added SuccessFully",insertCustomer));
			}
			else
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Customer Doesnot Save"));
			
		}
		catch(Exception e)
		{
			throw new InternalServerErrorException("Int Server Error");
		}
	}
    
     
    @Operation(summary = "Find Customer By Id",description = "e commerece online books store  Find the Customer")
    @ApiResponses({
    	@ApiResponse(responseCode = "201",description = "Getting Customer ById successfully"),
    	@ApiResponse(responseCode = "400",description = "Customer Not Found"),
    	@ApiResponse(responseCode = "500",description = "Internal server error")
    })
   
	@GetMapping("/findcustomer/{id}")
	public ResponseEntity<ResponseMassage> updateCutomer(@PathVariable Long id)
	{
		
			Customer findcustomerbyId = customerService.findcustomerbyId(id);
			
			if (findcustomerbyId!=null)
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_ACCEPTED,Constant.SUCCESS,"Customer Found",findcustomerbyId));
			}
			else
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Customer Not Found"));
			}
		
	}
    
    
    
    @Operation(summary = "Customer Update their Details",description = "e commerece online books store  Update the Customer")
    @ApiResponses({
    	@ApiResponse(responseCode = "201",description = "Customer Update successfully"),
    	@ApiResponse(responseCode = "400",description = "Customer Not Update"),
    	@ApiResponse(responseCode = "500",description = "Internal server error")
    })
  
	@PutMapping("/updatecustomer")
	public ResponseEntity<ResponseMassage> updateCutomer(@RequestBody Customer customer)
	{
		try
		{
			if(customer.getId()==null)
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Please Provide Customer ID"));
			}
			Customer updateCustomer = customerService.updateCustomer(customer);
			if(updateCustomer!=null)
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_ACCEPTED,Constant.SUCCESS,"Customer Updated ",updateCustomer));
			}
			else
			{
				return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Customer Not Found"));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILURE,"Server Error"));

		}
	}
	
	
    @Operation(summary = "Customer Can Save & Update their Details",description = "If Customer id Present it will Update Otherwise it will Save")
    @ApiResponses({
    	@ApiResponse(responseCode = "201",description = "Customer Update or Save successfully"),
    	@ApiResponse(responseCode = "400",description = "Customer Not Update"),
    	@ApiResponse(responseCode = "500",description = "Internal server error")
    })
   
	@PostMapping("/saveorupdate")
	public ResponseEntity<ResponseMassage> saveorupdatecustomer(@RequestBody Customer customer)
	{
		try
		{
			if (customer.getFname() == null || customer.getFname().isEmpty() || customer.getMail() == null || customer.getMail().isEmpty())
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST, Constant.FAILED,"First Name And Mail Shouldnot empty or null"));
			}
			if(customer.getId()==null)
			{
				Customer saveorupdate = customerService.saveorupdate(customer);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMassage(HttpURLConnection.HTTP_OK, Constant.SUCCESS,"Customer Saved SuccessFully",saveorupdate));

			}
			else
			{
				Customer saveorupdate = customerService.saveorupdate(customer);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMassage(HttpURLConnection.HTTP_OK, Constant.SUCCESS,"Customer Updated ",saveorupdate));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILURE,"Server Error"));

		}
		
	}
	
	
	
	
    @Operation(summary = "Getting All Customer Details",description = "Retribe the all Customer Details")
    @ApiResponses({
    	@ApiResponse(responseCode = "201",description = "Getting All Customers Details successfully"),
    	@ApiResponse(responseCode = "400",description = "All Customer Not Retrived"),
    	@ApiResponse(responseCode = "500",description = "Internal server error")
    })
    
	@GetMapping("/getallcustomer")
	public ResponseEntity<ResponseMassage> getallcustomer()
	{
		try
		{
			List<Customer> getallcustomerdetails = customerService.getallcustomerdetails();
			if(getallcustomerdetails!=null)
			{
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMassage(HttpURLConnection.HTTP_OK, Constant.SUCCESS,"All Customer Retrived",getallcustomerdetails));
			}
			else
			{
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST, Constant.FAILED,"Nothing to find"));
			}
		}
		catch(Exception e)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_INTERNAL_ERROR,Constant.FAILURE,"Server Error"));
		}
	}
	
	
	
	@DeleteMapping("/deletecustomer/{id}")
	public ResponseEntity<ResponseMassage> deletebook(@PathVariable Long id)
	{
		if(id==null)
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Please Mention Book Id"));
		}
		
		String deletebookmsg = customerService.deletebook(id);
		
		return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_OK,Constant.SUCCESS,deletebookmsg));
	}
		
	
	
}
