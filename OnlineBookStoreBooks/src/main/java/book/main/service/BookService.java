package book.main.service;

import java.util.List;

import book.main.entity.BookDetails;
import book.main.model.BookDetailsDTO;

public interface BookService 
{
	public BookDetails insertbooks(BookDetailsDTO book);

	public BookDetails findbookbyid(Long id);

	public List<BookDetails> getallbookdetails();

	public BookDetails saveorupdatebook(BookDetailsDTO book);

	public String deletebook(Long id);

}
