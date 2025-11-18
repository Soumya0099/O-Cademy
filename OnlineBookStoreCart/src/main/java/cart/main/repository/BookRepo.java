package cart.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cart.main.entity.BookDetails;

public interface BookRepo extends JpaRepository<BookDetails, Long>
{

}
