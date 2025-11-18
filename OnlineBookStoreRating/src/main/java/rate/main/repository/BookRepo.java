package rate.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rate.main.entity.BookDetails;

public interface BookRepo extends JpaRepository<BookDetails, Long>
{

}
