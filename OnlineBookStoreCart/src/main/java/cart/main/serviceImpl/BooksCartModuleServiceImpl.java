package cart.main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cart.main.entity.BookDetails;
import cart.main.entity.BooksCartModule;
import cart.main.entity.Customer;
import cart.main.exception.BookIdNotExistException;
import cart.main.exception.CustomerNotFoundException;
import cart.main.repository.BookRepo;
import cart.main.repository.BooksCartModuleRepo;
import cart.main.repository.CustomerRepo;
import cart.main.service.BooksCartModuleService;

@Service
public class BooksCartModuleServiceImpl implements BooksCartModuleService
{
	@Autowired
	private BooksCartModuleRepo booksCartModuleRepo;
     
	@Autowired
	private BookRepo bookRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	

	@Override
	public BooksCartModule addbookdtocart(int quantity, Long bookid, Long customerid) 
	{
		BookDetails existbook = bookRepo.findById(bookid).orElseThrow(()->new BookIdNotExistException("Book id Not Found"));
		
		Customer existcustomer = customerRepo.findById(customerid).orElseThrow(()->new CustomerNotFoundException("Customer Not Found"));
		
		BooksCartModule cart = booksCartModuleRepo.findByCustomerAndBook(existcustomer,existbook);
		
		if(cart!=null)
		{
			cart.setQuantity(cart.getQuantity()+quantity);
		}
		else
		{
			cart=new BooksCartModule(quantity,existbook,existcustomer);
		}
		
		cart.setTotalprice(existbook.getPrice()*cart.getQuantity());
		
		BooksCartModule savedcart = booksCartModuleRepo.save(cart);
		
		
		return savedcart;
	}


	@Override
	public List<BooksCartModule> showcartitems() 
	{
		List<BooksCartModule> allitemsfromcart = booksCartModuleRepo.findAll();
		return allitemsfromcart;
	}

}
