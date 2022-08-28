package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Exceptions.idNotFoundException;
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

public class EditBlacklistController implements Serializable, Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button removeblacklist;

	@FXML
	private ComboBox<Customer> addTo;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private Button addblacklist;

	@FXML
	private ComboBox<Customer> removeFrom;

	@FXML
	private Parent parent;

	@FXML
	void EditBlacklist(ActionEvent event) throws NothingIsSelectedException, FailedToException {

		try {
			if((addblacklist.isPressed() && addTo.getValue()==null) || (removeblacklist.isPressed() && removeFrom.getValue()==null)) {
				throw new NothingIsSelectedException();
			}

			else if(addblacklist.isPressed()&& addTo.getValue()!=null) {

				int ID=addTo.getValue().getId();

				for(Integer id: Model.Restaurant.getInstance().getCustomers().keySet())
				{
					if(id==ID)
					{
						if(Model.Restaurant.getInstance().addCustomerToBlackList(Model.Restaurant.getInstance().getCustomers().get(ID))) {
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully added");
						}

						else {
							throw new FailedToException("add");
						}
					}

				}
			}
			
			else if(removeblacklist.isPressed()&& removeFrom.getValue()!=null) {

				for(Customer c: Model.Restaurant.getInstance().getBlackList())
				{
					if(c.equals(removeFrom.getValue()))
						{
							Model.Restaurant.getInstance().getBlackList().remove(c);
							Model.Restaurant.savaData();
							Model.Restaurant.LoadDB();
							initialize(null,null);
							others.MyMethods.infoMessage("Success!", "Successfully removed");	
						}
					else throw new FailedToException("remove");
					
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
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		HashMap<Integer, Customer> AllCustomers = Model.Restaurant.getInstance().getCustomers();
		HashSet<Customer> BlackListCustomers = Model.Restaurant.getInstance().getBlackList();

		for(Customer c: AllCustomers.values()) {
			addTo.getItems().add(c);
		}

		for(Customer c: BlackListCustomers) {
			removeFrom.getItems().add(c);
		}


	}

}
