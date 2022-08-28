package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Customer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class RemoveCustomerController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button removecust;

	@FXML
	private ComboBox<Customer> customers;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	void removeCustomer(ActionEvent event) throws FailedToException {

		try {
			if(customers.getValue()==null)
				throw new NothingIsSelectedException();

			HashMap<Integer, Customer > returnedCust = Model.Restaurant.getInstance().getCustomers();
			
			for(Customer c: returnedCust.values())
			{
				System.out.println("1");
				
				if(c.getId()==customers.getValue().getId()) 
				{
					System.out.println("2");
					
					if(Model.Restaurant.getInstance().removeCustomer(c))
					{
						System.out.println("3");
						others.MyMethods.infoMessage("Success!", "Successfully removed");
						Model.Restaurant.savaData();
						Model.Restaurant.LoadDB();
						initialize(null,null);
						//others.MyMethods.infoMessage("Success!", "Successfully removed");
					}

					else 
						throw new FailedToException("remove");
				}
			}
		 
		}
		catch(NothingIsSelectedException e) {
			e.getMessage();
		}
		catch(FailedToException e) {
			e.getMessage();
		}
	}

	@FXML
	void back(ActionEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/ManagerMainPage.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					screen.getScene().getWindow().hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@FXML
	void logout(ActionEvent event) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				try {
					parent = FXMLLoader.load(getClass().getResource("/view/Restaurant.fxml"));
					Stage stage = new Stage();
					Scene scene = new Scene(parent);
					stage.setScene(scene);
					stage.show();
					screen.getScene().getWindow().hide();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		HashMap<Integer, Customer> cust = Model.Restaurant.getInstance().getCustomers();

		for(Customer c: cust.values())
		{
			customers.getItems().add(c);
		}

	}

}
