package Exceptions;



public class ErrorMessage extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ErrorMessage(String message) {

		super(message);
		title = "Error!";
	}


}
