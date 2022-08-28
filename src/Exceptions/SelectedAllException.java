package Exceptions;

public class SelectedAllException  extends ErrorMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SelectedAllException() {

		super("You have selected all of the choices! \nPlease select one of the values");
	}
}
