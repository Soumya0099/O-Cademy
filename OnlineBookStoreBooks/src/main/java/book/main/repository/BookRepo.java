package book.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import book.main.entity.BookDetails;

public interface BookRepo extends JpaRepository<BookDetails, Long>
{

}
