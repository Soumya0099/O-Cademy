package order.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import order.main.entity.OrderModule;

public interface OrderRepo extends JpaRepository<OrderModule, Long>
{

}
