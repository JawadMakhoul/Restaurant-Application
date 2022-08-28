package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

import Exceptions.ErrorMessage;
import Model.Customer;
import Model.Dish;
import Utils.Gender;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class getRelevantDishListManagerController implements Initializable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@FXML
	private AnchorPane screen;

	@FXML
	private Button getrelevantdishlist;

	@FXML
	private Button back;

	@FXML
	private Button logout;

	@FXML
	private ListView<Customer> customer;
	private HashMap<Integer, Customer> AllCustomers;

	@FXML
	private ListView<Dish> relevantDishlist;

	@FXML
	private Parent parent;

	@FXML
	void GetRelevantDishList(ActionEvent event) throws ErrorMessage {

		ArrayList<Dish> returned = Model.Restaurant.getInstance().getReleventDishList(Model.Restaurant.getInstance().getRealCustomer(others.MyMethods.getCustomer().getId()));

		if(!returned.isEmpty())
		{
			if(customer.getSelectionModel().getSelectedItem().getId()==others.MyMethods.getCustomer().getId()) {

				for(Dish d:returned)
				{
					relevantDishlist.getItems().add(d);
				}
			}
		}

		else throw new ErrorMessage(null);
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

		AllCustomers = Model.Restaurant.getInstance().getCustomers();
		for(Customer c: AllCustomers.values())
		{
			customer.getItems().add(c);
		}
	}

}
