package Exceptions;

public class illegalTimeToMakeException extends Exception {

	private static final long serialVersionUID = 1L;

	public illegalTimeToMakeException() {
		super("Illegal 'time to make' Input! Please fill it again.");
	}
}
