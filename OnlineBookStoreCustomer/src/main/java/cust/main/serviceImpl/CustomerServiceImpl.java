package cust.main.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cust.main.entity.Customer;
import cust.main.exception.CustomerNotFoundException;
import cust.main.repository.CustomerRepo;
import cust.main.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService
{
	@Autowired
	private CustomerRepo customerRepo;

	@Override
	@Cacheable(value = "insertcustomer")
	public Customer insertCustomer(Customer customer) 
	{
		Customer save = customerRepo.save(customer);
		return save;
		
	}

	@Override
	@Cacheable(value = "customerbyid")
	public Customer findcustomerbyId(Long id) 
	{
		Optional<Customer> byId = customerRepo.findById(id);
		if(byId.isPresent())
		{
			Customer customer = byId.get();
			
			if (customer != null) 
			{
				return customer;
			}
		}
		else
		{
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return null;
	}

	@Override
	@Cacheable(value = "updatecustomer")
	public Customer updateCustomer(Customer customer) 
	{
		Customer save = customerRepo.save(customer);
		if (save != null)
			return save;
		else
			return null;
	}

	@Override
	
	public Customer saveorupdate(Customer customer) 
	{
		if(customer.getId()==null)
		{
	         customerRepo.save(customer);	
		}
		else
		{
			Optional<Customer> byId = customerRepo.findById(customer.getId());
			if(byId.isPresent())
			{
				Customer existcustmer = customerRepo.save(customer);
				existcustmer.setFname(customer.getFname());
				existcustmer.setLname(customer.getLname());
				existcustmer.setMail(customer.getMail());
			}
			else
			{
				throw new RuntimeException("User Not Found");
			}
		}
		return customer;
		
	}

	@Override
	@Cacheable(value = "allcustomer")
	public List<Customer> getallcustomerdetails() 
	{
		List<Customer> customerlist = customerRepo.findAll();
		
		return customerlist;
	}

	@Override
	@Cacheable(value = "deletecustomer")
	public String deletebook(Long id) {
		Optional<Customer> byId = customerRepo.findById(id);
		if(byId.isPresent())
		{
			customerRepo.deleteById(id);
			return "Customer Deleted Succesfully";
		}
		else
		{
			throw new CustomerNotFoundException("Customer Id Not Exist");
		}
	}

}
