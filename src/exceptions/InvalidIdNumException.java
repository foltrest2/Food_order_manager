package exceptions;

@SuppressWarnings("serial")
public class InvalidIdNumException extends Exception{

	public InvalidIdNumException() {
		super("The ID entry doesn't exists");
	}

}
