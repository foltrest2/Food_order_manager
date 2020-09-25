package exceptions;

@SuppressWarnings("serial")
public class InvalidRouteException extends Exception{

	public InvalidRouteException() {
		super("The file indicated in the route doesn't exists");
	}

}
