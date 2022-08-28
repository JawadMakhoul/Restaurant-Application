package others;

import java.io.Serializable;
import Model.Customer;
import others.MyMethods;
import javafx.scene.control.Alert;


public class MyMethods implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Customer cust = null;//this variable is used to determine which customer is logged in
	
	// static Methods:
	

	public static Customer getCustomer() {
		return cust;
	}

	public static void setCustomer(Customer cust) {
		MyMethods.cust = cust;
	}
	
	public static void infoMessage(String title, String message) {//creates a pop-up internal message 
		try {
			Alert alert;
			if (title.equals("FAILED!") || title.equals("ERROR!")) {
				
				alert = new Alert(Alert.AlertType.ERROR);
			} else {
				
				alert = new Alert(Alert.AlertType.INFORMATION);
			}
			alert.setTitle(title);
			alert.setContentText(message);
			alert.setHeaderText(null);
			alert.showAndWait();
		} catch (Error e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean CheckPassword(String password) {
		
		if(password.length()>=8) {
			int numbers_counter=0, Capital_letters_Counter=0;
			
			for(Character chaar: password.toCharArray())
			{
				if(chaar>=65 && chaar<=90)
					Capital_letters_Counter++;
				if(chaar>=48 && chaar<=57)
					numbers_counter++;
				if(numbers_counter > 0 && Capital_letters_Counter > 0)
					return true;
			}
		}
		
		return false;
	}	
	
}
