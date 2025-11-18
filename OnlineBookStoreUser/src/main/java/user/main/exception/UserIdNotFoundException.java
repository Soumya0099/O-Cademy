package user.main.exception;

public class UserIdNotFoundException extends RuntimeException
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4L;
	
	public UserIdNotFoundException(String msg)
	{
		super(msg);
	}

}
