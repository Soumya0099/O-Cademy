package cart.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cart.main.entity.BookDetails;
import cart.main.entity.BooksCartModule;
import cart.main.entity.Customer;

@Repository
public interface BooksCartModuleRepo extends JpaRepository<BooksCartModule, Long>
{

	public BooksCartModule findByCustomerAndBook(Customer customer,BookDetails book);

}
