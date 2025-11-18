package user.main.entity;

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
@Table(name = "register")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegister 
{
	@Id   //For Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Generate id number
	private Long id;
	@Column(name = "fname")
	private String fname;
	@Column(name = "lname")
	private String lname;
	@Column(name = "email")
	private String email;
	@Column(name = "password")
	private String password;
	@Column(name = "contact")
	private long contact;
   
}
