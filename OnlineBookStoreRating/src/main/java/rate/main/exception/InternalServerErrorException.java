package rate.main.exception;

public class InternalServerErrorException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	public InternalServerErrorException(String msg)
	{
		super(msg);
	}

}
