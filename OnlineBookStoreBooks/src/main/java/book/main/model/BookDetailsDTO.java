package book.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDetailsDTO 
{
	private Long id;
	
	private String bookname;
	
	private String author;
	
	private String publisher;
	
	private int price;

}
