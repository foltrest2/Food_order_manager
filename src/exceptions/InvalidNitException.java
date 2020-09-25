package exceptions;

@SuppressWarnings("serial")
public class InvalidNitException extends Exception {

	public InvalidNitException() {
		super("The nit entry doesn't exists");
	}

}
