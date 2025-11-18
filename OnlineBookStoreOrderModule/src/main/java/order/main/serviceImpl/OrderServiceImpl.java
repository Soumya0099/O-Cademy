package order.main.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import order.main.entity.Customer;
import order.main.entity.OrderModule;
import order.main.model.OrderDto;
import order.main.repository.CustomerRepo;
import order.main.repository.OrderRepo;
import order.main.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService
{
	@Autowired private CustomerRepo customerRepo;
	@Autowired private OrderRepo orderRepo;
	
	public Boolean checkPrime(Long id)
	{
		 return customerRepo.findById(id).map(Customer::getPrime).orElse(false);
	}

	@Override
	public String createOrder(OrderDto orderDto) 
	{
		
		Boolean checkPrime = checkPrime(orderDto.getCustomerid());
		
		if(!checkPrime)
		{
			return "You Are not a prime Member";
		}
		else
		{
			OrderModule orderModule = new OrderModule();
			List<String> bookname = orderDto.getBookname();
			
			orderModule.setBookname(bookname);
			orderModule.setCustomerid(orderDto.getCustomerid());
			
			orderRepo.save(orderModule);
			
			return "You Are a Prime Member";
			
		}
	}

}
