package cart.main.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksCartModule 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private int quantity;
	@Column
	private int totalprice;
	
	@ManyToOne
	@JoinColumn(name="bookid",nullable = false)
	private BookDetails book;
	
	@ManyToOne
	@JoinColumn(name="customerid",nullable = false)
	private Customer customer;
	
	@Column(name = "createdTime")
	@UpdateTimestamp
	private LocalDateTime createdtime;

	public BooksCartModule(int quantity, BookDetails bookDetails, Customer customer) {
		super();
		this.quantity = quantity;
		this.book = bookDetails;
		this.customer = customer;
	}
}
