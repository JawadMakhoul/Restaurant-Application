package Exceptions;

public class illegalPriceException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public illegalPriceException() {
		super("Illegal price Input! Please fill it again.");
	}
}
