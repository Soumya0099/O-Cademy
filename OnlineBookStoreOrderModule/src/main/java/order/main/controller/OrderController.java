package order.main.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import order.main.model.OrderDto;
import order.main.model.ResponseMassage;
import order.main.service.OrderService;
import order.main.utility.Constant;

@RestController
@RequestMapping("/order")
public class OrderController
{
	@Autowired
	private OrderService orderService;
	@PostMapping("/createorder")
	public ResponseEntity<ResponseMassage> createorder(@RequestBody OrderDto orderDto)
	{
		if(orderDto.getCustomerid()==null || orderDto.getBookname()==null || orderDto.getBookname().isEmpty())
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_BAD_REQUEST,Constant.FAILED,"Please Select At leat One Book"));
		}
		
		String order = orderService.createOrder(orderDto);
		
		if(order.toLowerCase().contains("not"))
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"No",order));
		}
		else
		{
			return ResponseEntity.ok(new ResponseMassage(HttpURLConnection.HTTP_CREATED,Constant.SUCCESS,"Yses",order));
		}
		
		
	}

}
