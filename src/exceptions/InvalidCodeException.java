package exceptions;

@SuppressWarnings("serial")
public class InvalidCodeException extends Exception{

	public InvalidCodeException() {
		super("The code entry doesn't exists");
	}

}
