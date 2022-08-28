package Exceptions;

public class illegalAgeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public illegalAgeException() {
		super("Your age is illegal to signup.");
	}

}
