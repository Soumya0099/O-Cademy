package book.main.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMassage 
{
	private Integer statuscode;
	private String status;
	private String massage;
	private Object data;
	private String decodepass;
	private List<?> list;

	public ResponseMassage(Integer statuscode, String status, String massage, List<?> list) {
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
		this.list = list;
	}

	public ResponseMassage(Integer statuscode, String status, String massage, Object data) {
		
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
		this.data = data;
	}

	public ResponseMassage(Integer statuscode, String status, String massage) {
		
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
	}

	public ResponseMassage(Integer statuscode, String status, String massage, Object data, String decodepass) {
		super();
		this.statuscode = statuscode;
		this.status = status;
		this.massage = massage;
		this.data = data;
		this.decodepass = decodepass;
	}

}
