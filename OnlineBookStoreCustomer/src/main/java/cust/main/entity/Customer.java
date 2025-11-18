package cust.main.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "phone")
	private long phone;
	
	@Column(name = "prime",columnDefinition = "TINYINT")
    private Boolean prime;
	
	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime createdate;
	
	@UpdateTimestamp
	@Column(name = "updatedate")
	private LocalDateTime updatedate;

}

