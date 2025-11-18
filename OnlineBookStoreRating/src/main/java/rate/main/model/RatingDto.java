package rate.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto 
{
	private long customerid;
	private long bookid;
	private int star;
	private String description;

}
