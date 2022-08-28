package Exceptions;

public class WrongBirthdayDate extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WrongBirthdayDate() {
		super("Unacceptable birthday date! Please choose again.");
	}

}
