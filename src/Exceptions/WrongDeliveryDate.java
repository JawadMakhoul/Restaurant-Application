package Exceptions;

public class WrongDeliveryDate extends Exception {

private static final long serialVersionUID = 1L;
	
	public WrongDeliveryDate() {
		super("Unacceptable delivery date! Please choose again.");
	}

}
