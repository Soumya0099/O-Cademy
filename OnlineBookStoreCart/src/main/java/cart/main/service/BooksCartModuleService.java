package cart.main.service;

import java.util.List;

import cart.main.entity.BooksCartModule;


public interface BooksCartModuleService {

	public BooksCartModule addbookdtocart(int quantity, Long bookid, Long customerid);

	List<BooksCartModule>  showcartitems();

}
