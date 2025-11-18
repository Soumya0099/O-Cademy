package book.main.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import book.main.entity.BookDetails;
import book.main.exception.BookIdNotExistException;
import book.main.model.BookDetailsDTO;
import book.main.repository.BookRepo;
import book.main.service.BookService;

@Service
public class BookServiceImpl implements BookService
{
	@Autowired
	BookRepo bookrepo;

	@Override
	@Cacheable(value = "inserbook")
	public BookDetails insertbooks(BookDetailsDTO book) 
	{
		BookDetails bookDetails = new BookDetails();
		bookDetails.setBookname(book.getBookname());
		bookDetails.setAuthor(book.getAuthor());
		bookDetails.setPublisher(book.getPublisher());
		bookDetails.setPrice(book.getPrice());
		
		 BookDetails savedbook = bookrepo.save(bookDetails);
		if(savedbook!=null)
		{
			return savedbook;
		}
		else
		{
			return null;
		}
	}

	@Override
	@Cacheable(value = "findbook")
	public BookDetails findbookbyid(Long id) {
		Optional<BookDetails> bookbyId = bookrepo.findById(id);
		if(bookbyId.isPresent())
		{
//			System.out.println("Api Hit ");
			return bookbyId.get();
		}
		else
		{
			throw new BookIdNotExistException("Id Not Found");
		}
	}

	@Override
	@Cacheable(value = "allbook")
	public List<BookDetails> getallbookdetails() {
		List<BookDetails> allbook = bookrepo.findAll();
		if(allbook!=null)
			return allbook;
		return null;
	}

	@Override
	@Cacheable(value = "saveorupdateBook")
	public BookDetails saveorupdatebook(BookDetailsDTO book) 
	{
		if(book.getId()==null)
		{
			BookDetails bookDetails = new BookDetails();
			bookDetails.setBookname(book.getBookname());
			bookDetails.setAuthor(book.getAuthor());
			bookDetails.setPublisher(book.getPublisher());
			bookDetails.setPrice(book.getPrice());
			
			BookDetails saved2books = bookrepo.save(bookDetails);
			
			return saved2books;
		}
		else
		{
			Optional<BookDetails> byId = bookrepo.findById(book.getId());
			if(byId.isPresent())
			{
				BookDetails existbook = byId.get();
				existbook.setBookname(book.getBookname());
				existbook.setAuthor(book.getAuthor());
				existbook.setPublisher(book.getPublisher());
				existbook.setPrice(book.getPrice());
				
				BookDetails updatebook = bookrepo.save(existbook);
				
				return updatebook;		
			}
			else
			{
				throw new BookIdNotExistException("Book Id Not Exist");
			}
		}
		
	}

	@Override
	@Cacheable(value = "deletebook")
	public String deletebook(Long id) {
		Optional<BookDetails> byId = bookrepo.findById(id);
		
		if(byId.isPresent())
		{
			bookrepo.deleteById(id);
			return "Book SuccessFully Deleted";	
		}
		else
		{
			throw new BookIdNotExistException("Book Id Not Exist");
		}
		
	}

}
