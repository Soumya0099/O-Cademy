package user.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDTO 
{
	private Long id;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private long contact;
	

}
