package rate.main.exception;

public class BookIdNotExistException extends RuntimeException
{

	private static final long serialVersionUID = 3L;
	
	public BookIdNotExistException(String msg)
	{
		super(msg);
	}

}
