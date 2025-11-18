package rate.main.model;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMassages 
{
	private Integer statuscode;
	private String status;
	private String massage;
	
	private List<?> list;
	
	private Map<?,?> map;

	public ErrorMassages(Integer statuscode, String status, String massage, Map<?, ?> map) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
		this.map = map;
	}

	public ErrorMassages(Integer statuscode, String status, String massage, List<?> list) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
		this.list = list;
	}

}
