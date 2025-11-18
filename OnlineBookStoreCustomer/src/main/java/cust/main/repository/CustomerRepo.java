package cust.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cust.main.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long>
{

}
