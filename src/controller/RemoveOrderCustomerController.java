package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import Exceptions.FailedToException;
import Exceptions.NothingIsSelectedException;
import Model.Customer;
import Model.Order;
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

public class RemoveOrderCustomerController implements Serializable,Initializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private Parent parent;

	@FXML
	private AnchorPane screen;

	@FXML
	private ComboBox<Order> orders;

	private TreeSet<Order> AllOrders;

	@FXML
	private Button removeorder;

	@FXML
	private Button back;

	@FXML
	private Button logout;



	@FXML
	void removeorder(ActionEvent event) throws NothingIsSelectedException, FailedToException {
		try {
			if(orders.getValue()==null)
				throw new NothingIsSelectedException();

			else {
				boolean flag=Model.Restaurant.getInstance().removeOrder(orders.getValue());

				if(flag)
				{
					Model.Restaurant.savaData();
					Model.Restaurant.LoadDB();
					initialize(null,null);
					others.MyMethods.infoMessage("Success!", "Successfully added");
				}
				else throw new FailedToException("remove");
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
					parent = FXMLLoader.load(getClass().getResource("/view/CustomerMainPage.fxml"));
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
		HashMap<Customer, TreeSet<Order>> returned = Model.Restaurant.getInstance().getOrderByCustomer();

		for(Customer c: returned.keySet())
		{
			if(c.equals(others.MyMethods.getCustomer()))
				AllOrders= returned.get(c);
		}

		for(Order o: AllOrders)
		{
			orders.getItems().add(o);
		}

	}

}
