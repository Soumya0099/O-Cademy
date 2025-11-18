package cart.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cart.main.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>
{

}
