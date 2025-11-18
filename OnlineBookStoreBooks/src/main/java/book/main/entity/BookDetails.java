package book.main.entity;

import java.time.LocalDate;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "bookname")
	private String bookname;
	@Column(name = "author")
	private String author;
	@Column(name = "publisher")
	private String publisher;
	@Column(name="price")
	private int price;
	@UpdateTimestamp
	@Column(name = "savetime")
	private LocalDate savetime;

}
