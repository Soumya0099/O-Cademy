package cust.main.service;

import java.util.List;

import cust.main.entity.Customer;

public interface CustomerService {
	public Customer insertCustomer(Customer Customer);

	public Customer findcustomerbyId(Long id);

	public Customer updateCustomer(Customer customer);

	public Customer saveorupdate(Customer customer);

	public List<Customer> getallcustomerdetails();

	public String deletebook(Long id);

}
