package rate.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rate.main.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>
{

}
